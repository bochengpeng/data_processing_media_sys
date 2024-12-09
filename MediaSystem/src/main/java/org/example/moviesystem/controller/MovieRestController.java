package org.example.moviesystem.controller;

import org.example.moviesystem.model.Movie;
import org.example.moviesystem.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MovieRestController {

    private final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getTopRatedMovies() {
        return movieService.getTopRatedMovies();
    }
}
