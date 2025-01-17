-- ============================================
-- 1. Create Roles
-- ============================================

-- Create custom roles with LOGIN privileges
CREATE ROLE junior_user LOGIN PASSWORD '1234';
CREATE ROLE medior_user LOGIN PASSWORD 'medior_password';
CREATE ROLE senior_user LOGIN PASSWORD 'senior_password';
CREATE ROLE api_user LOGIN PASSWORD 'api_password';

-- ============================================
-- 2. Grant Table Permissions
-- ============================================

-- Grant SELECT on specific tables to Juniors
GRANT SELECT ON users, profiles, series, movies, episodes, contents, watch_list, watchlist_saved_content TO junior_user;

-- Revoke access to sensitive tables from Juniors
REVOKE SELECT ON user_preferences, viewing_history, viewing_session FROM junior_user;

-- Grant SELECT on specific tables to Mediors
GRANT SELECT ON users, profiles, series, movies, episodes, contents, user_preferences, watch_list, watchlist_saved_content, viewing_history TO medior_user;

-- Revoke access to financial data from Mediors
REVOKE SELECT ON viewing_session FROM medior_user;

-- Grant ALL privileges on all tables to Seniors
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO senior_user;

-- ============================================
-- 3. Grant Sequence Permissions
-- ============================================

-- Grant ALL privileges on all sequences to Seniors
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO senior_user;

-- If other roles need specific access to sequences, grant accordingly
-- Example: Grant USAGE and SELECT on all sequences to Mediors
-- GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO medior_user;

-- ============================================
-- 4. Configure API User Permissions
-- ============================================

-- Revoke all direct table access from API user
REVOKE ALL ON ALL TABLES IN SCHEMA public FROM api_user;

-- Grant EXECUTE on all functions to API user
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA public TO api_user;

-- Note: Views will be handled separately after their creation

-- ============================================
-- 5. Set Default Privileges for Future Objects
-- ============================================

-- Grant SELECT on tables to Juniors and Mediors by default
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO junior_user, medior_user;

-- Grant EXECUTE on functions to API user by default
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO api_user;

-- ============================================
-- 6. Column-Level Access Restrictions
-- ============================================

-- Restrict Juniors from accessing sensitive columns in 'users'
REVOKE SELECT (password, username) ON users FROM junior_user;

-- Restrict Juniors from accessing financial data in 'user_preferences'
REVOKE SELECT (*) ON subscriptions FROM junior_user;

-- Note: Ensure that column-level revokes are supported in your PostgreSQL version

-- ============================================
-- 7. Create and Grant Permissions on Views
-- ============================================

-- Example: Create a view for API user to access limited user information
CREATE VIEW api_user_limited_users AS
SELECT user_id, email, is_activated
FROM users;

-- Grant SELECT on the view to API user
GRANT SELECT ON api_user_limited_users TO api_user;

-- Repeat the above CREATE VIEW and GRANT SELECT steps for other necessary views
-- Example:
-- CREATE VIEW api_user_watch_list AS
-- SELECT id, user_id, content_id, added_at
-- FROM watch_list;
-- GRANT SELECT ON api_user_watch_list TO api_user;

-- Ensure that new tables inherit default privileges
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO junior_user, medior_user, senior_user;

-- Ensure that new functions inherit default privileges
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO api_user;
