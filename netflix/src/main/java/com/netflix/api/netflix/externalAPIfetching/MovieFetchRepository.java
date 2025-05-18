package com.netflix.api.netflix.externalAPIfetching;

import com.netflix.api.netflix.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieFetchRepository extends JpaRepository<Movie, Integer>
{
}