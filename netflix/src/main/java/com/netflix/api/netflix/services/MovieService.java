package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.MovieDto;
import com.netflix.api.netflix.models.*;
import com.netflix.api.netflix.repository.MovieRepository;
import com.netflix.api.netflix.repository.ProfileRepository;
import com.netflix.api.netflix.repository.ViewingHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//@Service
//public class MovieService
//{
//
//    private final MovieRepository movieRepository;
//    private final ProfileRepository profileRepository;
//    private final ViewingHistoryRepository viewingHistoryRepository;
//
//    public MovieService(
//            MovieRepository movieRepository,
//            ProfileRepository profileRepository,
//            ViewingHistoryRepository viewingHistoryRepository)
//    {
//        this.movieRepository = movieRepository;
//        this.profileRepository = profileRepository;
//        this.viewingHistoryRepository = viewingHistoryRepository;
//    }
//
//    public Movie addMovie(Movie movie)
//    {
//        return movieRepository.save(movie);
//    }
//
//    public Movie getMovieById(int movieId)
//    {
//        return movieRepository.findById(movieId)
//                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + movieId));
//    }
//
//    public List<Movie> getAllMovies()
//    {
//        return movieRepository.findAll();
//    }
//
//    public Movie updateMovie(int movieId, Movie updatedMovie)
//    {
//        Movie existingMovie = getMovieById(movieId);
//        existingMovie.setTitle(updatedMovie.getTitle());
////        existingMovie.setDuration(updatedMovie.getDescription());
//        existingMovie.setGenre(updatedMovie.getGenre());
//        existingMovie.setAgeRating(updatedMovie.getAgeRating());
//        existingMovie.setDuration(updatedMovie.getDuration());
//        existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
////        existingMovie.setDuration(updatedMovie.getSupportedQualities());
//
//        return movieRepository.save(existingMovie);
//    }
//
//    public void deleteMovie(int movieId)
//    {
//        movieRepository.deleteById(movieId);
//    }
//
//    public List<Movie> getMovies(String title, Genre genre, ContentClassification classification, AgeRating ageRating)
//    {
//        if (title != null)
//        {
//            return movieRepository.findByTitleIgnoreCase(title);
//        }
////        else if (genre != null && classification != null)
////        {
////            return movieRepository.findByGenreAndClassification(genre, classification);
////        }
//        else if (genre != null)
//        {
//            return movieRepository.findByGenre(genre);
//        }
//        else if (classification != null)
//        {
//            return movieRepository.findByContentClassification(classification);
//        }
//        else if (ageRating != null)
//        {
//            return movieRepository.findByAgeRatingLessThanEqual(ageRating);
//        }
//        else
//        {
//            return movieRepository.findAll();
//        }
//    }
//
//    public void watchMovie(int movieId, int profileId) {
//        // Fetch the movie
//        Movie movie = movieRepository.findById(movieId)
//                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + movieId));
//
//        // Fetch the profile
//        Profile profile = profileRepository.findById(profileId)
//                .orElseThrow(() -> new IllegalArgumentException("Profile not found with ID: " + profileId));
//
//        // Create a viewing history record
//        ViewingHistory history = new ViewingHistory();
//        history.setMovie(movie);
//        history.setProfile(profile);
//        history.setViewedAt(LocalDateTime.now());
//        history.setStopAt(null);
//
//        // Save the viewing history
//        viewingHistoryRepository.save(history);
//    }
//}

public interface MovieService {
    MovieDto createMovie(MovieDto movieDto);
    MovieDto getMovieById(int movieId);
    List<MovieDto> getAllMovies();
    MovieDto updateMovie(int movieId, MovieDto movieDto);
    void deleteMovie(int movieId);
}