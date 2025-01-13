package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.services.MovieFetchService;
import com.netflix.api.netflix.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MovieFetchController
{
    private final MovieFetchService movieFetchService;

    public MovieFetchController(MovieFetchService movieFetchService) {
        this.movieFetchService = movieFetchService;
    }

    @GetMapping("/api/movies")
    public String getMovies(Model model) {
        List<Movie> movies = movieFetchService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movie";
    }
}
