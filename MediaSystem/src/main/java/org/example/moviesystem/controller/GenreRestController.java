package org.example.moviesystem.controller;

import org.example.moviesystem.repository.GenreRepository;
import org.example.moviesystem.service.GenreService;
import org.example.moviesystem.table.Genre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreRestController
{
    private final GenreService genreService;
    private final GenreRepository genreRepository;

    public GenreRestController(GenreService genreService, GenreRepository genreRepository)
    {
        this.genreService = genreService;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/genres")
    public List<Genre> getAllGenres()
    {
        return genreService.getAllGenres();
    }

    // PUT method to update genre
    @PutMapping("/genres/{genreId}")
    public Genre updateGenre(@PathVariable Long genreId, @RequestBody Genre genreName)
    {
        return genreService.updateGenre(genreId, genreName);
    }

    //Add new genre
    @PostMapping(("/new_genres"))
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre)
    {
        if (genre.getGenreName() == null || genre.getGenreName().isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }

        Genre savedGenre = genreRepository.save(genre);

        return ResponseEntity.ok(savedGenre);
    }
}
