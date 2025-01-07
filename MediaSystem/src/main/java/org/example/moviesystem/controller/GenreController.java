package org.example.moviesystem.controller;

import org.example.moviesystem.service.GenreService;
import org.example.moviesystem.table.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Controller
public class GenreController
{
    private final GenreService genreService;

    public GenreController(GenreService genreService)
    {
        this.genreService = genreService;
    }

    @GetMapping("/api/genres")
    public String getGenres(Model model)
    {
        List<Genre> allGenres = genreService.getAllGenres();
        model.addAttribute("genres", allGenres);  // Add the genres to the model
        return "genre";  // This will return the genre.html view
    }
}
