package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.MovieDto;

import java.util.List;

public interface MovieService
{
    MovieDto createMovie(MovieDto dto);
    MovieDto getMovieById(int id);
    List<MovieDto> getAllMovies();
    MovieDto updateMovie(int id, MovieDto dto);
    void deleteMovie(int id);
}