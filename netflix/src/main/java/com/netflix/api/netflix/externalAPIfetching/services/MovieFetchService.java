package com.netflix.api.netflix.externalAPIfetching.services;

import com.netflix.api.netflix.externalAPIfetching.MovieFetchRepository;
import com.netflix.api.netflix.models.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieFetchService
{
    private final MovieFetchRepository movieFetchRepository;

    public MovieFetchService(MovieFetchRepository movieFetchRepository)
    {
        this.movieFetchRepository = movieFetchRepository;
    }

    public List<Movie> getAllMovies()
    {
        return this.movieFetchRepository.findAll();
    }
}
