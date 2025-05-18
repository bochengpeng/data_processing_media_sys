package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer>
{
     @Query("SELECT e FROM Episode e WHERE e.series.id = :seriesId")
     List<Episode> findBySeriesId(int seriesId);
}
