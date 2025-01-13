package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieFetchRepository extends JpaRepository<Movie, Integer>
{
    // You can add custom query methods here if needed
}