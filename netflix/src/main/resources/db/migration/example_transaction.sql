BEGIN TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- 1. Create user (make sure function includes required fields)
SELECT add_user('exampleuser@example.com', 'StrongPassword123');

-- 2. Get the newly created user ID
-- NOTE: This assumes email is unique
WITH new_user AS (
    SELECT user_id FROM users WHERE email = 'exampleuser@example.com'
)
-- 3. Add a profile and log via procedure
SELECT add_profile_with_log(user_id, 'UserProfile1', 25)
FROM new_user;

-- 4. Update watchlist (assumes content_id = 1 exists)
SELECT update_watchlist(
               (SELECT profile_id FROM profiles WHERE user_id = (SELECT user_id FROM users WHERE email = 'exampleuser@example.com') LIMIT 1),
               1
       );

COMMIT;
