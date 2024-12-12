package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.AgeRating;
import com.netflix.api.netflix.models.ContentClassification;
import com.netflix.api.netflix.models.Genre;
import com.netflix.api.netflix.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer>
{
    // Find movies by title (case-insensitive)
    List<Movie> findByTitleIgnoreCase(String title);

    // Find movies suitable for a specific age
    List<Movie> findByAgeRatingLessThanEqual(AgeRating age);

    // Fetch movies by viewing classification
    List<Movie> findByContentClassification(ContentClassification classification);

    List<Movie> findByGenre(Genre genre);
}
