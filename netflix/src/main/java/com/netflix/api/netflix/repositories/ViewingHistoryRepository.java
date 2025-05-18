package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.ViewingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewingHistoryRepository extends JpaRepository<ViewingHistory, Integer>
{
    @Query("SELECT vh FROM ViewingHistory vh WHERE vh.profile.profileId = :profileId")
    List<ViewingHistory> findByProfileId(int profileId);
}
