package org.example.moviesystem.service;

import org.example.moviesystem.repository.GenreRepository;
import org.example.moviesystem.table.Genre;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenreService
{
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository)
    {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres()
    {
        return genreRepository.findAll();
    }

    // Method to update genre in the database
    public Genre updateGenre(Long genreId, Genre updatedGenre)
    {
        Genre existingGenre = genreRepository.findById(String.valueOf(genreId)).orElseThrow(() -> new RuntimeException("Genre not found"));
        existingGenre.setGenreName(updatedGenre.getGenreName());  // Update the genre name
        return genreRepository.save(existingGenre);  // Save the updated genre to the database
    }
}