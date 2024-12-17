package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.WatchListDto;

import java.util.List;

public interface WatchListService
{
    WatchListDto createWatchlist(int userId, WatchListDto watchlistDto);
    WatchListDto getWatchlistById(int watchlistId);
    List<WatchListDto> getWatchlistsByUserId(int userId);
    WatchListDto updateWatchlist(int watchlistId, WatchListDto watchlistDto);
    void deleteWatchlist(int watchlistId);
}
