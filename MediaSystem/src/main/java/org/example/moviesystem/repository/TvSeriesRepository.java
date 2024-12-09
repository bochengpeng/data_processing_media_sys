package org.example.moviesystem.repository;

import org.example.moviesystem.model.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvSeriesRepository extends JpaRepository<TvSeries, Long>
{
}
