package com.nhlstenden.netflixrefactor.externalApiFetching;

import com.nhlstenden.netflixrefactor.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieFetchRepository extends JpaRepository<Movie, Integer>
{
}