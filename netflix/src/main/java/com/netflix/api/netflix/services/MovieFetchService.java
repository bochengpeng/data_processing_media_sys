package com.netflix.api.netflix.services;

import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.repository.MovieFetchRepository;
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
        return movieFetchRepository.findAll();
    }
}
