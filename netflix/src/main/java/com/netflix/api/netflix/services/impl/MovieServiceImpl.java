package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.MovieDto;
import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.repositories.MovieRepository;
import com.netflix.api.netflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService
{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Transactional
    public MovieDto createMovie(MovieDto dto)
    {
        try
        {
            Movie movie = mapToEntity(dto);
            Movie saved = this.movieRepository.save(movie);
            return mapToDto(saved);

        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create movie: " + e.getMessage(), e);
        }
    }

    @Override
    public MovieDto getMovieById(int id)
    {
        try
        {
            Movie movie = this.movieRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            return mapToDto(movie);

        } catch (Exception e)
        {
            throw new RuntimeException("Failed to get movie by id: " + e.getMessage(), e);
        }
    }

    @Override
    public List<MovieDto> getAllMovies()
    {
        try
        {
            return this.movieRepository.findAll().stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());

        } catch (Exception e)
        {
            throw new RuntimeException("Failed to get all movies: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public MovieDto updateMovie(int id, MovieDto dto)
    {
        try
        {
            Movie movie = this.movieRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Movie not found"));

            movie.setTitle(dto.getTitle());
            movie.setDescription(dto.getDescription());
            movie.setReleaseDate(dto.getReleaseDate());
            movie.setDuration(dto.getDuration());
            movie.setGenre(dto.getGenre());
            movie.setContentClassification(dto.getContentClassification());
            movie.setAgeRating(dto.getAgeRating());
            movie.setAvailableQualities(dto.getAvailableQualities());
            movie.setDirector(dto.getDirector());
            movie.setActors(dto.getActors());

            Movie updated = this.movieRepository.save(movie);
            return mapToDto(updated);

        } catch (Exception e)
        {
            throw new RuntimeException("Failed to update movie: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deleteMovie(int id)
    {
        try
        {
            this.movieRepository.deleteById(id);

        } catch (Exception e)
        {
            throw new RuntimeException("Failed to delete movie: " + e.getMessage(), e);
        }
    }

    private MovieDto mapToDto(Movie movie)
    {
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movie.getId());  // Note: id from Content base class
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setGenre(movie.getGenre());
        movieDto.setDuration(movie.getDuration());
        movieDto.setAgeRating(movie.getAgeRating());
        movieDto.setReleaseDate(movie.getReleaseDate());
        movieDto.setAvailableQualities(movie.getAvailableQualities());
        movieDto.setDirector(movie.getDirector());
        movieDto.setActors(movie.getActors());
        movieDto.setContentClassification(movie.getContentClassification());

        return movieDto;
    }

    private Movie mapToEntity(MovieDto movieDto)
    {
        Movie movie = new Movie();

        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setGenre(movieDto.getGenre());
        movie.setDuration(movieDto.getDuration());
        movie.setAgeRating(movieDto.getAgeRating());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setAvailableQualities(movieDto.getAvailableQualities());
        movie.setDirector(movieDto.getDirector());
        movie.setActors(movieDto.getActors());
        movie.setContentClassification(movieDto.getContentClassification());

        return movie;
    }
}