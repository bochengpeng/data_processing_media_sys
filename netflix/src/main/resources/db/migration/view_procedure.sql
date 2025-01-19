-- Create database users with different privileges
CREATE USER api_user WITH PASSWORD 'api_password';
CREATE USER analyst_user WITH PASSWORD 'analyst_password';
CREATE USER admin_user WITH PASSWORD 'admin_password';
CREATE USER reporting_user WITH PASSWORD 'report_password';

-- Create Views
-- View for public movie information (used by API)
CREATE OR REPLACE VIEW public.vw_public_movies
AS
SELECT movie_id,
       title,
       duration,
       release_date,
       description,
       age_rating,
       content_classification,
       genre
FROM movies m
WHERE movie_id > 0;

-- View for analytical purposes (used by analysts)
CREATE VIEW vw_user_engagement AS
SELECT
    p.profile_id,
    p.user_id,  -- Assuming Profile has a reference to User
    u.username,
    m.movie_id,
    m.title,
    m.duration,
    vh.watched_percentage,
    vh.viewed_at,
    vh.stop_at,
    CASE
        WHEN vh.watched_percentage = 100 THEN 'Completed'
        ELSE 'In Progress'
        END AS watch_status,
    COUNT(vh.history_id) OVER (PARTITION BY p.profile_id) AS total_viewings
FROM profiles p
         JOIN users u ON p.user_id = u.user_id
         JOIN viewing_history vh ON p.profile_id = vh.profile_id
         JOIN movies m ON vh.movie_id = m.movie_id;

-- Stored Procedures
-- Procedure to add new movie with validation
CREATE OR REPLACE FUNCTION sp_add_movie(
    p_title VARCHAR,
    p_duration INT,
    p_genre_id INT,
    p_age_rating SMALLINT,
    p_content_classification SMALLINT,
    p_description TEXT,
    p_release_date DATE
) RETURNS VOID AS $$
BEGIN
    -- Validate inputs
    IF p_duration <= 0 THEN
        RAISE EXCEPTION 'Duration must be positive';
END IF;

    -- Insert movie
INSERT INTO movies (
    title,
    duration,
    genre,
    age_rating,
    content_classification,
    description,
    release_date
) VALUES (
             p_title,
             p_duration,
             p_genre_id,
             p_age_rating,
             p_content_classification,
             p_description,
             p_release_date
         );
END;
$$ LANGUAGE plpgsql;

-- Procedure for generating movie reports
CREATE OR REPLACE FUNCTION sp_generate_movie_report(
    p_start_date DATE,
    p_end_date DATE
) RETURNS TABLE(
                   title VARCHAR,
                   total_views INT,  -- Number of times the movie was viewed
                   total_watch_time INT,  -- Total watch time (sum of watched time in minutes)
                   average_watched_percentage NUMERIC  -- Average watched percentage
               ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            m.title,
            COUNT(vh.history_id) AS total_views,  -- Count the number of views for the movie
            SUM(EXTRACT(EPOCH FROM (vh.stop_at - vh.viewed_at)) / 60) AS total_watch_time,  -- Total watch time in minutes
            AVG(vh.watched_percentage) AS average_watched_percentage  -- Average percentage watched by users
        FROM movies m
                 LEFT JOIN viewing_history vh ON m.movie_id = vh.movie_id
        WHERE vh.viewed_at BETWEEN p_start_date AND p_end_date
        GROUP BY m.movie_id, m.title
        ORDER BY total_views DESC;  -- Ordering by most viewed movies
END;
$$ LANGUAGE plpgsql;


-- Triggers
-- Trigger to validate age rating
CREATE OR REPLACE FUNCTION trg_movie_age_rating_validate() RETURNS TRIGGER AS $$
BEGIN
    IF NEW.age_rating NOT IN (0, 6, 12, 16, 18) THEN
        RAISE EXCEPTION 'Invalid age rating';
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_movie_age_rating_validate
    BEFORE INSERT ON movies
    FOR EACH ROW
    EXECUTE FUNCTION trg_movie_age_rating_validate();

-- Grant appropriate privileges to users
-- API User (minimal read access)
GRANT SELECT ON vw_public_movies TO api_user;

-- Analyst User (read access to analytical views and basic functions)
GRANT SELECT ON vw_user_engagement TO analyst_user;
GRANT EXECUTE ON FUNCTION sp_generate_movie_report TO analyst_user;

-- Admin User (full access)
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO admin_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO admin_user;

-- Reporting User (read-only access to specific views and reporting functions)
GRANT SELECT ON vw_user_engagement TO reporting_user;
GRANT EXECUTE ON FUNCTION sp_generate_movie_report TO reporting_user;

-- Set appropriate transaction isolation levels
SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL READ COMMITTED;
