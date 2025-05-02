CREATE ROLE junior WITH LOGIN PASSWORD 'junior_secure_password';
CREATE ROLE medior WITH LOGIN PASSWORD 'medior_secure_password';
CREATE ROLE senior WITH LOGIN PASSWORD 'senior_secure_password';
CREATE ROLE api_user WITH LOGIN PASSWORD 'api_secure_password';

-- Step 2: Revoke default privileges
REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON ALL TABLES IN SCHEMA public FROM PUBLIC;
REVOKE ALL ON ALL FUNCTIONS IN SCHEMA public FROM PUBLIC;

-- Step 4: Permissions for JUNIOR (no access to financial or sensitive data)
GRANT CONNECT ON DATABASE netflix_new_db TO junior;
GRANT USAGE ON SCHEMA public TO junior;
GRANT SELECT ON user_public_view, content_public_view TO junior;

-- Step 5: Permissions for MEDIOR (no financial info, but gets more than junior)
GRANT CONNECT ON DATABASE netflix_new_db TO medior;
GRANT USAGE ON SCHEMA public TO medior;
GRANT SELECT ON user_public_view, content_public_view, profiles, users, profile_preferred_genres, profile_viewing_classifications, rating, recommendation, contents, series, movie TO medior;

-- Step 6: Permissions for SENIOR (full read/write access)
GRANT CONNECT ON DATABASE netflix_new_db TO senior;
GRANT USAGE ON SCHEMA public TO senior;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO senior;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA public TO senior;

-- Step 7: Permissions for API_USER (only access views & stored procedures)
GRANT CONNECT ON DATABASE netflix_new_db TO api_user;
GRANT USAGE ON SCHEMA public TO api_user;
GRANT SELECT ON user_public_view, content_public_view TO api_user;

-- Example: Stored procedures for API usage
-- GRANT EXECUTE ON FUNCTION add_user(...) TO api_user;
-- GRANT EXECUTE ON FUNCTION get_watchlist_by_profile(...) TO api_user;
