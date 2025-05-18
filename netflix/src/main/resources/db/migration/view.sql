CREATE VIEW InterestedInSeriesProfiles AS
SELECT wl.*
FROM watch_list wl
         JOIN profiles p ON wl.profile_id = p.profile_id
WHERE p.interested_in_series = TRUE;

CREATE VIEW InterestedInMoviesProfiles AS
SELECT wl.*
FROM watch_list wl
         JOIN profiles p ON wl.profile_id = p.profile_id
WHERE p.interested_in_films = TRUE;

CREATE VIEW content_public_view AS
SELECT c.id, c.title, c.description, c.release_date, m.director, s.total_seasons
FROM contents c
         LEFT JOIN movies m ON c.id = m.id
         LEFT JOIN series s ON c.id = s.id;