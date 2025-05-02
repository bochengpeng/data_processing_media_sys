package com.nhlstenden.netflixrefactor.externalApiFetching.controllers;

import com.nhlstenden.netflixrefactor.externalApiFetching.services.MovieFetchService;
import com.nhlstenden.netflixrefactor.models.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MovieFetchController
{
    private final MovieFetchService movieFetchService;

    public MovieFetchController(MovieFetchService movieFetchService)
    {
        this.movieFetchService = movieFetchService;
    }

    @GetMapping("/api/movies")
    public String getMovies(Model model)
    {
        List<Movie> movies = movieFetchService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movie";
    }
}
