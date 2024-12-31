package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.MovieDto;
import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.repository.MovieRepository;
import com.netflix.api.netflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService
{
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        // Convert DTO to entity
        Movie movie = mapToEntity(movieDto);

        // Save the movie entity to the database
        Movie savedMovie = movieRepository.save(movie);

        // Convert the saved entity back to DTO and return
        return mapToDto(savedMovie);
    }

    @Override
    public MovieDto getMovieById(int movieId) {
        // Retrieve movie by ID from the database
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Convert entity to DTO and return
        return mapToDto(movie);
    }

    @Override
    public List<MovieDto> getAllMovies() {
        // Get all movies from the database
        List<Movie> movieList = movieRepository.findAll();

        // Convert each movie entity to DTO
        return movieList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto updateMovie(int movieId, MovieDto movieDto) {
        // Retrieve existing movie from the database
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Update the movie's fields with values from the DTO
        existingMovie.setTitle(movieDto.getTitle());
        existingMovie.setDescription(movieDto.getDescription());
//        existingMovie.setGenre(movieDto.getGenres());
        existingMovie.setDuration(movieDto.getDuration());
//        existingMovie.setAgeRating(movieDto.getAgeRating());

        // Save the updated movie entity to the database
        Movie updatedMovie = movieRepository.save(existingMovie);

        // Convert the updated entity to DTO and return
        return mapToDto(updatedMovie);
    }

    @Override
    public void deleteMovie(int movieId) {
        // Retrieve movie by ID
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Delete the movie entity
        movieRepository.delete(movie);
    }

    // Convert Movie entity to MovieDto
    private MovieDto mapToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
//        movieDto.setContentId(movie.getMovieId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
//        movieDto.setGenres(movie.getGenre());
        movieDto.setDuration(movie.getDuration());
//        movieDto.setAgeRating(movie.getAgeRating());
        return movieDto;
    }

    // Convert MovieDto to Movie entity
    private Movie mapToEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
//        movie.setGenre(movieDto.getGenres());
        movie.setDuration(movieDto.getDuration());
//        movie.setAgeRating(movieDto.getAgeRating());
        return movie;
    }
}
