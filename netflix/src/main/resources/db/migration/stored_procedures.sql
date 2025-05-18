CREATE OR REPLACE FUNCTION add_user(_email TEXT, _password TEXT)
    RETURNS VOID AS $$
BEGIN
    INSERT INTO users (
        email,
        password,
        referral_discount_applied,
        is_activated,
        created_at
    )
    VALUES (
               _email,
               _password,
               false,
               true,
               NOW()
           );
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_watchlist(_profile_id BIGINT, _content_id BIGINT)
    RETURNS VOID AS $$
BEGIN
    INSERT INTO watch_list(profile_id, content_id)
    VALUES (_profile_id, _content_id)
    ON CONFLICT DO NOTHING;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE action_logs (
                             log_id SERIAL PRIMARY KEY,
                             user_id INT NOT NULL,
                             action_type VARCHAR(50) NOT NULL,
                             description TEXT,
                             created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT fk_user
                                 FOREIGN KEY(user_id)
                                     REFERENCES users(user_id)
                                     ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION add_profile_with_log(
    p_user_id INT,
    p_profile_name TEXT,
    p_age INT
)
    RETURNS VOID AS $$
BEGIN
    INSERT INTO profiles(user_id, name, age, interested_in_films, interested_in_series)
    VALUES (p_user_id, p_profile_name, p_age, true, true);

    INSERT INTO action_logs(user_id, action_type, description, created_at)
    VALUES (p_user_id, 'CREATE_PROFILE', 'Profile created via procedure', NOW());
END;
$$ LANGUAGE plpgsql;

SELECT add_profile_with_log(1, 'My Profile', 20);
