package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer>
{
}
