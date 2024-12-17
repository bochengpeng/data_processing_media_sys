package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Content;
import com.netflix.api.netflix.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer>
{
    List<Content> findByGenre(Genre genre);
    List<Content> findByAgeRatingLessThanEqual(int ageRating);
}
