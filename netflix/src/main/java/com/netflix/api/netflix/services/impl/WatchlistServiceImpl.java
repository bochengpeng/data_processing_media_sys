package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.WatchListDto;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.models.WatchList;
import com.netflix.api.netflix.repository.ContentRepository;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.repository.WatchListRepository;
import com.netflix.api.netflix.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchlistServiceImpl implements WatchListService
{

    private final WatchListRepository watchlistRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Autowired
    public WatchlistServiceImpl(WatchListRepository watchlistRepository, UserRepository userRepository, ContentRepository contentRepository) {
        this.watchlistRepository = watchlistRepository;
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
    }

    @Override
    public WatchListDto createWatchlist(int userId, WatchListDto watchlistDto) {
        // Fetch the user by ID to associate the watchlist with the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert WatchlistDto to Watchlist entity
        WatchList watchlist = mapToEntity(watchlistDto);
//        /watchlist.setUser(user); // Associate the watchlist with the user

        // Save the watchlist entity
        WatchList savedWatchlist = watchlistRepository.save(watchlist);

        // Convert the saved watchlist entity to WatchlistDto and return it
        return mapToDto(savedWatchlist);
    }

    @Override
    public WatchListDto getWatchlistById(int watchlistId) {
        // Retrieve the watchlist by its ID
        WatchList watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        // Convert Watchlist entity to WatchlistDto and return it
        return mapToDto(watchlist);
    }

    @Override
    public List<WatchListDto> getWatchlistsByUserId(int userId)
    {
        return null;
    }


    @Override
    public WatchListDto updateWatchlist(int watchlistId, WatchListDto watchlistDto) {
        // Retrieve the existing watchlist by ID
        WatchList existingWatchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        // Update the watchlist fields with the data from the DTO
//        existingWatchlist.setContentIds(watchlistDto.getContentIds());

        // Save the updated watchlist entity
        WatchList updatedWatchlist = watchlistRepository.save(existingWatchlist);

        // Convert the updated watchlist entity to WatchlistDto and return it
        return mapToDto(updatedWatchlist);
    }

    @Override
    public void deleteWatchlist(int watchlistId) {
        // Retrieve the watchlist by ID
        WatchList watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        // Delete the watchlist entity
        watchlistRepository.delete(watchlist);
    }

    // Helper method to map Watchlist entity to WatchlistDto
    private WatchListDto mapToDto(WatchList watchlist) {
        WatchListDto watchlistDto = new WatchListDto();
        watchlistDto.setWatchListId(watchlist.getWatchListId());
//        watchlistDto.se(watchlist.getUser().getId());
//        watchlistDto.setContentIds(watchlist.getContentIds());
        return watchlistDto;
    }

    // Helper method to map WatchlistDto to Watchlist entity
    private WatchList mapToEntity(WatchListDto watchlistDto) {
        //        watchlist.setContentIds(watchlistDto.getContentIds());
        return new WatchList();
    }
}
