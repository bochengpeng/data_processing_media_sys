CREATE OR REPLACE FUNCTION log_profile_activity()
    RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO action_logs(user_id, action_type, description, created_at)
        VALUES (NEW.user_id, 'INSERT_PROFILE', 'New profile created', NOW());

    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO action_logs(user_id, action_type, description, created_at)
        VALUES (OLD.user_id, 'DELETE_PROFILE', 'Profile deleted', NOW());
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_log_profile_activity
    AFTER INSERT OR DELETE ON profiles
    FOR EACH ROW
EXECUTE FUNCTION log_profile_activity();

