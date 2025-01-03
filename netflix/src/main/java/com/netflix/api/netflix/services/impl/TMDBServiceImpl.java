package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.config.TMDBConfig;
import com.netflix.api.netflix.dto.TMDBMovieDTO;
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

    private Movie mapToEntity(TMDBMovieDTO tmdbMovieDTO)
    {
        Movie movie = new Movie();
        movie.setTitle(tmdbMovieDTO.getTitle());
        movie.setAgeRating((short) 0);
        movie.setDescription(tmdbMovieDTO.getOverview());
        movie.setReleaseDate(LocalDate.parse(tmdbMovieDTO.getRelease_date()));
        return movie;
    }
}

