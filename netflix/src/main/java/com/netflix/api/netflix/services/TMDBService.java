package com.netflix.api.netflix.services;

import com.netflix.api.netflix.models.Movie;

public interface TMDBService
{
    Movie fetchMovieDetails(int movieId);
}
