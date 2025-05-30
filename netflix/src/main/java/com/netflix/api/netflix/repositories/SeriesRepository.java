package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Integer>
{
    List<Series> findByTitleIgnoreCase(String title);
}
