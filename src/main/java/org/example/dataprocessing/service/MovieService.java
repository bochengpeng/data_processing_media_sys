package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.Movie;
import org.example.dataprocessing.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService
{

    private final MovieRepository movieRepository;

    // Constructor Injection
    public MovieService(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    // Create a new movie
    public Movie createMovie(Movie movie)
    {
        return movieRepository.save(movie);
    }

    // Get a movie by its ID
    public Optional<Movie> getMovieById(Long movieId)
    {
        return movieRepository.findById(movieId);
    }

    // Fetch all movies
    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }

    // Update an existing movie
    public Movie updateMovie(Long movieId, Movie updatedMovie)
    {
        return movieRepository.findById(movieId).map(existingMovie ->
        {
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setDescription(updatedMovie.getDescription());
            existingMovie.setGenres(updatedMovie.getGenres());
            existingMovie.setAgeRating(updatedMovie.getAgeRating());
            existingMovie.setSupportedQualities(updatedMovie.getSupportedQualities());
            existingMovie.setDuration(updatedMovie.getDuration());
            existingMovie.setCast(updatedMovie.getCast());
            existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
            return movieRepository.save(existingMovie);
        }).orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + movieId));
    }

    // Delete a movie by its ID
    public void deleteMovie(Long movieId)
    {
        if (!movieRepository.existsById(movieId))
        {
            throw new IllegalArgumentException("Movie not found with ID: " + movieId);
        }
        movieRepository.deleteById(movieId);
    }

    // Fetch movies by their release year
    public List<Movie> getMoviesByReleaseYear(int year)
    {
        return movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getReleaseDate() != null && movie.getReleaseDate().getYear() == year)
                .toList();
    }
}
