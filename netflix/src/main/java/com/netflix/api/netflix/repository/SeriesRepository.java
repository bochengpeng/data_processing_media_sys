package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Integer>
{
}
