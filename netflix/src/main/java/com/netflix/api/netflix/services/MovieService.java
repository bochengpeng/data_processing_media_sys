package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.MovieDto;
import com.netflix.api.netflix.models.*;
import com.netflix.api.netflix.repository.MovieRepository;
import com.netflix.api.netflix.repository.ProfileRepository;
import com.netflix.api.netflix.repository.ViewingHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieService {
    MovieDto createMovie(MovieDto movieDto);
    MovieDto getMovieById(int movieId);
    List<MovieDto> getAllMovies();
    MovieDto updateMovie(int movieId, MovieDto movieDto);
    void deleteMovie(int movieId);
}