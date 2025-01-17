package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.AgeRating;
import com.netflix.api.netflix.models.ContentClassification;
import com.netflix.api.netflix.models.Genre;
import com.netflix.api.netflix.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer>
{
    List<Movie> findByTitleIgnoreCase(String title);

    List<Movie> findByAgeRatingLessThanEqual(short ageRating);

    List<Movie> findByContentClassification(short contentClassification);

    List<Movie> findByGenre(short genre);
    @Query(value = "SELECT * FROM vw_public_movies", nativeQuery = true)
    List<Movie> findAllPublicMovies();
}
