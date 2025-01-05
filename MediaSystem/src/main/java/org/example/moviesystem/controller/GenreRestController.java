package org.example.moviesystem.controller;

import jakarta.servlet.http.HttpSession;
import org.example.moviesystem.service.GenreService;
import org.example.moviesystem.table.Genre;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreRestController
{
    private final GenreService genreService;

    public GenreRestController(GenreService genreService)
    {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public List<Genre> getAllGenres()
    {
        return genreService.getAllGenres();
    }

    // PUT method to update genre
    @PutMapping("/api/genres/{genreId}")
    public Genre updateGenre(@PathVariable String genreId, @RequestBody Genre genreName) {
        return genreService.updateGenre(genreId, genreName);
    }
}
