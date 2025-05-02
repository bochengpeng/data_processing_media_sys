CREATE OR REPLACE FUNCTION add_user(_email TEXT, _password TEXT)
RETURNS VOID AS $$
BEGIN
INSERT INTO users(email, password) VALUES (_email, _password);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_watchlist(_profile_id BIGINT, _content_id BIGINT)
RETURNS VOID AS $$
BEGIN
INSERT INTO watchlist_item(profile_id, content_id)
VALUES (_profile_id, _content_id)
    ON CONFLICT DO NOTHING;
END;
$$ LANGUAGE plpgsql;
