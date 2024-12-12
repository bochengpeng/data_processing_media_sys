package org.example.dataprocessing.controller;

import org.example.dataprocessing.entity.AgeRating;
import org.example.dataprocessing.entity.ContentClassification;
import org.example.dataprocessing.entity.Genre;
import org.example.dataprocessing.entity.Movie;
import org.example.dataprocessing.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        return ResponseEntity.ok(movieService.updateMovie(id, updatedMovie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) Genre genre,
                                                 @RequestParam(required = false) ContentClassification classification,
                                                 @RequestParam(required = false) String title,
                                                 @RequestParam(required = false) AgeRating ageRating) {
        List<Movie> movies = movieService.getMovies(title, genre, classification, ageRating);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/watch/{movieId}/{profileId}")
    public ResponseEntity<String> watchMovie(@PathVariable Long movieId, @PathVariable Long profileId) {
        movieService.watchMovie(movieId, profileId);
        return ResponseEntity.ok("Movie watched successfully.");
    }
}
