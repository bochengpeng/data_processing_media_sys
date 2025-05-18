package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.WatchListDto;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.Profile;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.models.WatchList;
import com.netflix.api.netflix.repositories.ProfileRepository;
import com.netflix.api.netflix.repositories.UserRepository;
import com.netflix.api.netflix.repositories.WatchListRepository;
import com.netflix.api.netflix.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchlistServiceImpl implements WatchListService
{

    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    @Transactional
    public WatchListDto addToWatchList(WatchListDto dto)
    {
        try
        {
            Profile profile = this.profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new RuntimeException("Profile not found"));

            WatchList entity = new WatchList();
            entity.setProfile(profile);
            entity.setContentId(dto.getContentId());
            entity.setContentType(dto.getContentType());
            entity.setAddedAt(LocalDateTime.now());

            WatchList saved = this.watchListRepository.save(entity);

            return mapToDto(saved);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to add item to watchlist: " + e.getMessage(), e);
        }
    }

    @Override
    public WatchListDto getWatchListItemById(int watchListId)
    {
        try
        {
            WatchList item = this.watchListRepository.findById(watchListId)
                    .orElseThrow(() -> new RuntimeException("WatchList item not found"));

            return mapToDto(item);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to fetch watchlist item: " + e.getMessage(), e);
        }
    }

    @Override
    public List<WatchListDto> getWatchListByProfileId(int profileId)
    {
        try
        {
            List<WatchList> list = this.watchListRepository.findByProfileId(profileId);
            return list.stream().map(this::mapToDto).collect(Collectors.toList());
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to retrieve watchlist: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public WatchListDto updateWatchListItem(int watchListId, WatchListDto dto)
    {
        try
        {
            WatchList existing = this.watchListRepository.findById(watchListId)
                    .orElseThrow(() -> new RuntimeException("WatchList item not found"));

            // Optionally validate or re-fetch profile
            Profile profile = this.profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new RuntimeException("Profile not found"));

            existing.setProfile(profile);
            existing.setContentId(dto.getContentId());
            existing.setContentType(dto.getContentType());
            existing.setAddedAt(LocalDateTime.now());

            WatchList updated = this.watchListRepository.save(existing);

            return mapToDto(updated);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to update watchlist item: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void removeFromWatchList(int watchListId)
    {
        try
        {
            if (!this.watchListRepository.existsById(watchListId))
            {
                throw new RuntimeException("WatchList item not found");
            }
            this.watchListRepository.deleteById(watchListId);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to remove item from watchlist: " + e.getMessage(), e);
        }
    }

    private WatchListDto mapToDto(WatchList entity)
    {
        WatchListDto watchListDto = new WatchListDto();
        watchListDto.setId(entity.getId());
        watchListDto.setContentId(entity.getContentId());
        watchListDto.setAddedAt(entity.getAddedAt());
        watchListDto.setProfileId(entity.getProfile().getProfileId());
        watchListDto.setContentType(entity.getContentType());

        return watchListDto;
    }
}