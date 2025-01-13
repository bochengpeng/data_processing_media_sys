package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.repository.MovieFetchRepository;
import com.netflix.api.netflix.services.MovieFetchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieFetchRestController
{
    private final MovieFetchService movieFetchService;
    private final MovieFetchRepository movieFetchRepository;

    public MovieFetchRestController(MovieFetchService movieFetchService, MovieFetchRepository movieFetchRepository)
    {
        this.movieFetchService = movieFetchService;
        this.movieFetchRepository = movieFetchRepository;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies()
    {
        return movieFetchService.getAllMovies();
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int movieId, @RequestBody Movie movieDetails) {
        // Fetch the existing movie by ID
        Optional<Movie> existingMovieOpt = movieFetchRepository.findById(movieId);

        if (existingMovieOpt.isPresent()) {
            Movie existingMovie = existingMovieOpt.get();

            // Update fields from the request body
            existingMovie.setDuration(movieDetails.getDuration());
            existingMovie.setTitle(movieDetails.getTitle());
            existingMovie.setAgeRating(movieDetails.getAgeRating());
            existingMovie.setContentClassification(movieDetails.getContentClassification());
            existingMovie.setGenre(movieDetails.getGenre());
            existingMovie.setDescription(movieDetails.getDescription());
            existingMovie.setReleaseDate(movieDetails.getReleaseDate());

            // Save and return the updated movie
            Movie updatedMovie = movieFetchRepository.save(existingMovie);
            return ResponseEntity.ok(updatedMovie);
        } else {
            // Return 404 if the movie is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/new_movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        // Validate mandatory fields (title, releaseDate, etc.)
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Title is required
        }
        if (movie.getReleaseDate() == null) {
            return ResponseEntity.badRequest().body(null); // Release date is required
        }
        if (movie.getDuration() <= 0) {
            return ResponseEntity.badRequest().body(null); // Duration must be positive
        }

        // Save the new movie into the database
        Movie savedMovie = movieFetchRepository.save(movie);

        // Return the saved movie as the response
        return ResponseEntity.ok(savedMovie);
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int movieId) {
        // Check if the movie exists in the database
        if (!movieFetchRepository.existsById(movieId)) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the movie does not exist
        }

        // Delete the movie
        movieFetchRepository.deleteById(movieId);

        // Return 204 No Content to indicate successful deletion
        return ResponseEntity.noContent().build();
    }
}
