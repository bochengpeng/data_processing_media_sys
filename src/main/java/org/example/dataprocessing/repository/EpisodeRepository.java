package org.example.dataprocessing.repository;

import org.example.dataprocessing.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository for Episode
@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long>
{
    List<Episode> findBySerie_SerieId(Long serieId);

    List<Episode> findBySerie_SerieIdAndSeasonNumber(Long serieId, int seasonNumber);
}
