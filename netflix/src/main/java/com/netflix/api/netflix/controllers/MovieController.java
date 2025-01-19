package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.MovieDto;
import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.repository.MovieRepository;
import com.netflix.api.netflix.services.impl.MovieServiceImpl;
import com.netflix.api.netflix.services.impl.TMDBServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController
{

    private final TMDBServiceImpl tmdbService;
    private final MovieRepository movieRepository;
    private final MovieServiceImpl movieService;

    public MovieController(TMDBServiceImpl tmdbService, MovieRepository movieRepository, MovieServiceImpl movieService)
    {
        this.tmdbService = tmdbService;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieFromExternalAPI(@PathVariable(value = "movieId") int movieId)
    {
        Movie movie = this.tmdbService.fetchMovieDetails(movieId);
        this.movieRepository.save(movie);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/allMovies")
    public ResponseEntity<List<MovieDto>> getAllMovies()
    {
        List<MovieDto> movies = this.movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(
            value = "/{id}/details",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<MovieDto> getMovieDetails(
            @PathVariable("id") int id,
            @RequestParam(value = "format", required = false) String format)
    {
        MovieDto movie = this.movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }
}