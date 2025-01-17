-- Create database users with different privileges
CREATE USER 'api_user'@'localhost' IDENTIFIED BY 'api_password';
CREATE USER 'analyst_user'@'localhost' IDENTIFIED BY 'analyst_password';
CREATE USER 'admin_user'@'localhost' IDENTIFIED BY 'admin_password';
CREATE USER 'reporting_user'@'localhost' IDENTIFIED BY 'report_password';

-- Create Views
-- View for public movie information (used by API)
CREATE VIEW vw_public_movies AS
SELECT
    m.movieId,
    m.title,
    m.duration,
    m.releaseDate,
    m.description,
    g.name as genre_name,
    c.rating_description as content_rating
FROM movies m
         JOIN genres g ON m.genre = g.genre_id
         JOIN content_classifications c ON m.contentClassification = c.classification_id
WHERE m.is_active = 1;

-- View for analytical purposes (used by analysts)
CREATE VIEW vw_movie_analytics AS
SELECT
    m.movieId,
    m.title,
    m.duration,
    m.releaseDate,
    COUNT(s.screening_id) as total_screenings,
    COUNT(DISTINCT t.ticket_id) as tickets_sold,
    SUM(t.price) as total_revenue
FROM movies m
         LEFT JOIN screenings s ON m.movieId = s.movie_id
         LEFT JOIN tickets t ON s.screening_id = t.screening_id
GROUP BY m.movieId, m.title, m.duration, m.releaseDate;

-- Stored Procedures
DELIMITER //

-- Procedure to add new movie with validation
CREATE PROCEDURE sp_add_movie(
    IN p_title VARCHAR(255),
    IN p_duration INT,
    IN p_genre_id INT,
    IN p_age_rating SMALLINT,
    IN p_content_classification SMALLINT,
    IN p_description TEXT,
    IN p_release_date DATE
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
ROLLBACK;
SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error occurred while adding movie';
END;

START TRANSACTION;

-- Validate inputs
IF p_duration <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Duration must be positive';
END IF;

    -- Insert movie
INSERT INTO movies (
    title,
    duration,
    genre,
    ageRating,
    contentClassification,
    description,
    releaseDate
) VALUES (
             p_title,
             p_duration,
             p_genre_id,
             p_age_rating,
             p_content_classification,
             p_description,
             p_release_date
         );

COMMIT;
END //

-- Procedure for generating movie reports
CREATE PROCEDURE sp_generate_movie_report(
    IN p_start_date DATE,
    IN p_end_date DATE
)
BEGIN
SELECT
    m.title,
    COUNT(s.screening_id) as screening_count,
    COUNT(t.ticket_id) as tickets_sold,
    SUM(t.price) as revenue
FROM movies m
         LEFT JOIN screenings s ON m.movieId = s.movie_id
         LEFT JOIN tickets t ON s.screening_id = t.screening_id
WHERE s.screening_date BETWEEN p_start_date AND p_end_date
GROUP BY m.movieId, m.title
ORDER BY revenue DESC;
END //

DELIMITER ;

-- Triggers
DELIMITER //

-- Trigger to log movie changes
CREATE TRIGGER trg_movie_audit_insert
    AFTER INSERT ON movies
    FOR EACH ROW
BEGIN
    INSERT INTO movie_audit_log (
        movie_id,
        action_type,
        action_date,
        user_id
    ) VALUES (
                 NEW.movieId,
                 'INSERT',
                 NOW(),
                 @current_user_id
             );
END //

-- Trigger to validate age rating
CREATE TRIGGER trg_movie_age_rating_validate
    BEFORE INSERT ON movies
    FOR EACH ROW
BEGIN
    IF NEW.ageRating NOT IN (0, 6, 12, 16, 18) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid age rating';
END IF;
END //

DELIMITER ;

-- Grant appropriate privileges to users
-- API User (minimal read access)
GRANT SELECT ON cinema_db.vw_public_movies TO 'api_user'@'localhost';

-- Analyst User (read access to analytical views and basic procedures)
GRANT SELECT ON cinema_db.vw_movie_analytics TO 'analyst_user'@'localhost';
GRANT EXECUTE ON PROCEDURE cinema_db.sp_generate_movie_report TO 'analyst_user'@'localhost';

-- Admin User (full access)
GRANT ALL PRIVILEGES ON cinema_db.* TO 'admin_user'@'localhost';

-- Reporting User (read-only access to specific views and reporting procedures)
GRANT SELECT ON cinema_db.vw_movie_analytics TO 'reporting_user'@'localhost';
GRANT EXECUTE ON PROCEDURE cinema_db.sp_generate_movie_report TO 'reporting_user'@'localhost';

-- Set appropriate transaction isolation levels
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;