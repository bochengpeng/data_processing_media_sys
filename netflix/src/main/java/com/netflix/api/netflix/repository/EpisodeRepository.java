package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer>
{
     @Query("SELECT e FROM Episode e WHERE e.series.seriesId = :seriesId")
     List<Episode> findBySeriesId(int seriesId);
}
