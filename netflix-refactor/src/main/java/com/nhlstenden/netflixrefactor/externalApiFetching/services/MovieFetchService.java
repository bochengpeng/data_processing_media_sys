package com.nhlstenden.netflixrefactor.externalApiFetching.services;

import com.nhlstenden.netflixrefactor.models.Movie;
import com.nhlstenden.netflixrefactor.externalApiFetching.MovieFetchRepository;
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
