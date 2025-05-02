CREATE VIEW user_public_view AS
SELECT id, email, subscription_id FROM users;

CREATE VIEW content_public_view AS
SELECT c.id, c.title, c.description, c.release_year, m.director, s.season_count
FROM contents c
         LEFT JOIN movie m ON c.id = m.id
         LEFT JOIN series s ON c.id = s.id;
