package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.WatchListDto;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.models.WatchList;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.repository.WatchListRepository;
import com.netflix.api.netflix.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WatchlistServiceImpl implements WatchListService
{

    private final WatchListRepository watchlistRepository;
    private final UserRepository userRepository;

    @Autowired
    public WatchlistServiceImpl(WatchListRepository watchlistRepository, UserRepository userRepository)
    {
        this.watchlistRepository = watchlistRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public WatchListDto createWatchlist(int userId, WatchListDto watchlistDto) throws UserNotFoundException
    {
        // Fetch the user by ID to associate the watchlist with the user
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Convert WatchlistDto to Watchlist entity
        WatchList watchlist = mapToEntity(watchlistDto);
//        /watchlist.setUser(user); // Associate the watchlist with the user

        // Save the watchlist entity
        WatchList savedWatchlist = this.watchlistRepository.save(watchlist);

        // Convert the saved watchlist entity to WatchlistDto and return it
        return mapToDto(savedWatchlist);
    }

    @Override
    public WatchListDto getWatchlistById(int watchlistId)
    {
        // Retrieve the watchlist by its ID
        WatchList watchlist = this.watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        // Convert Watchlist entity to WatchlistDto and return it
        return mapToDto(watchlist);
    }

    @Override
    public List<WatchListDto> getWatchlistsByProfileId(int profileId)
    {
        return null;
    }

    @Transactional
    @Override
    public WatchListDto updateWatchlist(int watchlistId, WatchListDto watchlistDto)
    {
        // Retrieve the existing watchlist by ID
        WatchList existingWatchlist = this.watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        // Update the watchlist fields with the data from the DTO
//        existingWatchlist.setContentIds(watchlistDto.getContentIds());

        // Save the updated watchlist entity
        WatchList updatedWatchlist = this.watchlistRepository.save(existingWatchlist);

        // Convert the updated watchlist entity to WatchlistDto and return it
        return mapToDto(updatedWatchlist);
    }

    @Transactional
    @Override
    public void deleteWatchlist(int watchlistId)
    {
        // Retrieve the watchlist by ID
        WatchList watchlist = this.watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        // Delete the watchlist entity
        this.watchlistRepository.delete(watchlist);
    }

    // Helper method to map Watchlist entity to WatchlistDto
    private WatchListDto mapToDto(WatchList watchlist)
    {
        WatchListDto watchlistDto = new WatchListDto();
        watchlistDto.setWatchListId(watchlist.getWatchListId());

        return watchlistDto;
    }

    // Helper method to map WatchlistDto to Watchlist entity
    private WatchList mapToEntity(WatchListDto watchlistDto)
    {
        WatchList watchList = new WatchList();
        watchList.setWatchListId(watchlistDto.getWatchListId());

        return watchList;
    }
}
