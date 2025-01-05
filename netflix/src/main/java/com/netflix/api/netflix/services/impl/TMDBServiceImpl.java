package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.config.TMDBConfig;
//import com.netflix.api.netflix.dto.ReleaseDate;
import com.netflix.api.netflix.dto.TMDBMovieDTO;
//import com.netflix.api.netflix.dto.TMDBReleaseDateResponse;
import com.netflix.api.netflix.models.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class TMDBServiceImpl
{

    private final RestTemplate restTemplate;
    private final TMDBConfig tmdbConfig;

    public TMDBServiceImpl(RestTemplate restTemplate, TMDBConfig tmdbConfig)
    {
        this.restTemplate = restTemplate;
        this.tmdbConfig = tmdbConfig;
    }

    public Movie fetchMovieDetails(int movieId)
    {
        String apiKey = tmdbConfig.getApiKey();
        String url = String.format("https://api.themoviedb.org/3/movie/%d?api_key=%s", movieId, apiKey);

        // Use RestTemplate to fetch the data
        ResponseEntity<TMDBMovieDTO> response = restTemplate.getForEntity(url, TMDBMovieDTO.class);

        TMDBMovieDTO movieDTO = response.getBody();

        if (movieDTO == null)
        {
            throw new RuntimeException("Failed to fetch movie details");
        }

        // Map DTO to Movie entity
        return mapToEntity(movieDTO);
    }

    private Movie mapToEntity(TMDBMovieDTO tmdbMovieDTO) {
        Movie movie = new Movie();
        movie.setTitle(tmdbMovieDTO.getTitle());

        // Default values for missing data
        movie.setAgeRating((short) getAgeRatingFromCertification(tmdbMovieDTO)); // Mapping logic for ageRating
        movie.setContentClassification((short) 0); // Update this logic when classification logic is available
        movie.setGenre(getGenreId(tmdbMovieDTO)); // Map first genre or use a default

        movie.setDuration(tmdbMovieDTO.getRuntime());
        movie.setDescription(tmdbMovieDTO.getOverview());
        movie.setReleaseDate(LocalDate.parse(tmdbMovieDTO.getRelease_date()));

        return movie;
    }

    // Example: Get age rating from certification
    private int getAgeRatingFromCertification(TMDBMovieDTO tmdbMovieDTO) {
        // Assuming the DTO has certifications information
        if (tmdbMovieDTO.getRelease_dates() != null) {
            return tmdbMovieDTO.getRelease_dates()
                    .stream()
                    .filter(cert -> cert.getIso_3166_1().equals("US")) // Filter for US certification
                    .findFirst()
                    .map(cert -> parseAgeRating(cert.getCertification())) // Example parser
                    .orElse(0);
        }
        return 0;
    }

    // Example: Map the first genre from the genre list to a short
    private short getGenreId(TMDBMovieDTO tmdbMovieDTO) {
        if (tmdbMovieDTO.getGenres() != null && !tmdbMovieDTO.getGenres().isEmpty()) {
            int firstGenreId = tmdbMovieDTO.getGenres().get(0).getId();

            // Map TMDb genre IDs to your allowed values
            return mapGenreId(firstGenreId);
        }
        return 1; // Default to a valid genre ID
    }

    private short mapGenreId(int tmdbGenreId) {
        // Example mapping based on TMDb genre IDs
        return switch (tmdbGenreId)
        {
            case 28 -> 1; // Action
            case 12 -> 2; // Adventure
            case 16 -> 3; // Animation
            case 35 -> 4; // Comedy
            case 80 -> 5; // Crime
            default -> 1; // Default to a valid genre
        };
    }

    // Example: Parse certification to age rating (implement logic as per requirement)
    private int parseAgeRating(String certification) {
        return switch (certification)
        {
            case "G" -> 1;
            case "PG" -> 2;
            case "PG-13" -> 3;
            case "R" -> 4;
            case "NC-17" -> 5;
            default -> 25;
        };
    }
}

