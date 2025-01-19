package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.WatchListDto;
import com.netflix.api.netflix.exception.UserNotFoundException;

import java.util.List;

public interface WatchListService
{
    WatchListDto createWatchlist(int userId, WatchListDto watchlistDto) throws UserNotFoundException;
    WatchListDto getWatchlistById(int watchlistId);
    List<WatchListDto> getWatchlistsByProfileId(int profileId);
    WatchListDto updateWatchlist(int watchlistId, WatchListDto watchlistDto);
    void deleteWatchlist(int watchlistId);
}
