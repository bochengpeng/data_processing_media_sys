package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Integer>
{
    @Query("SELECT w FROM WatchList w WHERE w.profile.profileId = :profileId\n")
    List<WatchList> findByProfileId(int profileId);
}
