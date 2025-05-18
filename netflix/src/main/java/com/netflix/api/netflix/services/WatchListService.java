package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.WatchListDto;
import com.netflix.api.netflix.exception.UserNotFoundException;

import java.util.List;

public interface WatchListService
{
    WatchListDto addToWatchList(WatchListDto dto);
    WatchListDto getWatchListItemById(int watchListId);
    List<WatchListDto> getWatchListByProfileId(int profileId);
    WatchListDto updateWatchListItem(int watchListId, WatchListDto dto);
    void removeFromWatchList(int watchListId);
}


