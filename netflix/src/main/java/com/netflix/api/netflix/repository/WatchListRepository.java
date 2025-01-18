package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Integer>
{
    // Find watchlist by profile ID
    WatchList findByProfile_ProfileId(int profileId);

    // Count number of saved contents in a watchlist
    int countSavedContentByProfile_ProfileId(int profileId);
}
