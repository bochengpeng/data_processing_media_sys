package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.MovieDto;
import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.repository.MovieRepository;
import com.netflix.api.netflix.services.TMDBService;
import com.netflix.api.netflix.services.impl.MovieServiceImpl;
import com.netflix.api.netflix.services.impl.TMDBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/movies")
//public class MovieController
//{
//    @Autowired
//    private MovieRepository movieRepository;
//
//    @GetMapping
//    public List<Movie> getAllMovies()
//    {
//        return movieRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Movie> getMovieById(@PathVariable int id)
//    {
//        return movieRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public Movie createMovie(@RequestBody Movie movie)
//    {
//        return movieRepository.save(movie);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody Movie movieDetails)
//    {
//        return movieRepository.findById(id)
//                .map(movie ->
//                {
//                    movie.setTitle(movieDetails.getTitle());
//                    movie.setDuration(movieDetails.getDuration());
//                    return ResponseEntity.ok(movieRepository.save(movie));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMovie(@PathVariable int id)
//    {
//        return movieRepository.findById(id)
//                .map(movie ->
//                {
//                    movieRepository.delete(movie);
//                    return ResponseEntity.ok().<Void>build();
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//}

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
    public ResponseEntity<Movie> getMovie(@PathVariable(value = "movieId") int movieId)
    {
        Movie movie = tmdbService.fetchMovieDetails(movieId);
        movieRepository.save(movie);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/allMovies")
    public ResponseEntity<List<MovieDto>> getAllMovies()
    {
        List<MovieDto> movies = movieService.getAllMovies();
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
        MovieDto movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }
}