package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.WatchList;
import org.example.dataprocessing.repository.WatchListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchListService
{

    private final WatchListRepository watchListRepository;

    public WatchListService(WatchListRepository watchListRepository)
    {
        this.watchListRepository = watchListRepository;
    }

    public List<WatchList> getWatchListByProfileId(Long profileId)
    {
        return watchListRepository.findByProfileId(profileId);
    }

    public WatchList addToWatchList(WatchList watchList)
    {
        return watchListRepository.save(watchList);
    }

    public void removeFromWatchList(Long watchListId)
    {
        watchListRepository.deleteById(watchListId);
    }
}
