package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.ViewingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViewingHistoryRepository extends JpaRepository<ViewingHistory, Integer>
{
    // Find viewing history by profile ID
    List<ViewingHistory> findByProfile_ProfileId(int profileId);

    // Find viewing history for a specific movie
    List<ViewingHistory> findByMovie_MovieId(Long movieId);

    // Find viewing history within a specific date range
    List<ViewingHistory> findByViewedAtBetween(LocalDateTime start, LocalDateTime end);

    // Count total viewing sessions for a specific profile
    int countByProfile_ProfileId(int profileId);

    // Find most recently viewed movies
    List<ViewingHistory> findTop10ByOrderByViewedAtDesc();

    // Find viewing history where the viewing session is incomplete (not stopped)
    List<ViewingHistory> findByStopAtIsNull();
}
