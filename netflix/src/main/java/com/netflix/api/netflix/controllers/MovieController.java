package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.MovieDto;
import com.netflix.api.netflix.services.MovieService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
@Slf4j
public class MovieController
{

    private final MovieService movieService;

    // Upload/Create a new movie
    @PostMapping("/upload")
    public ResponseEntity<?> createMovie(@RequestBody @Valid MovieDto dto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            MovieDto created = this.movieService.createMovie(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (ValidationException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            log.error("Failed to create movie: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the movie.");
        }
    }

    // Get a movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@PathVariable int id)
    {
        try
        {
            if (id <= 0) throw new ValidationException("ID must be positive.");
            MovieDto movie = this.movieService.getMovieById(id);

            return ResponseEntity.ok(movie);
        } catch (ValidationException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            log.error("Error fetching movie {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve movie.");
        }
    }

    // Get all movies
    @GetMapping("/view-all")
    public ResponseEntity<List<MovieDto>> getAllMovies()
    {
        List<MovieDto> movies = this.movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    // Update a movie
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody @Valid MovieDto dto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            if (id <= 0) throw new ValidationException("ID must be positive.");
            MovieDto updated = this.movieService.updateMovie(id, dto);
            return ResponseEntity.ok(updated);
        } catch (ValidationException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            log.error("Failed to update movie {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the movie.");
        }
    }

    // Delete a movie
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id)
    {
        try
        {
            if (id <= 0) throw new ValidationException("ID must be positive.");
            this.movieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        } catch (ValidationException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            log.error("Failed to delete movie {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the movie.");
        }
    }

    // Web view endpoint (for Thymeleaf or frontend template)
    @GetMapping
    public String getMoviesPage()
    {
        return "movie"; // View name for front-end (e.g., movie.html)
    }
}