package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.ViewingHistoryDto;
import com.netflix.api.netflix.dto.ViewingHistoryInternalDto;
import com.netflix.api.netflix.exception.ProfileNotFoundException;
import com.netflix.api.netflix.exception.ViewingHistoryNotFoundException;
import com.netflix.api.netflix.models.Episode;
import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.models.Profile;
import com.netflix.api.netflix.models.ViewingHistory;
import com.netflix.api.netflix.repository.EpisodeRepository;
import com.netflix.api.netflix.repository.MovieRepository;
import com.netflix.api.netflix.repository.ProfileRepository;
import com.netflix.api.netflix.repository.ViewingHistoryRepository;
import com.netflix.api.netflix.services.ViewingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewingHistoryServiceImpl implements ViewingHistoryService
{
    private final ViewingHistoryRepository viewingHistoryRepository;
    private final ProfileRepository profileRepository;
    private final EpisodeRepository episodeRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ViewingHistoryServiceImpl(ViewingHistoryRepository viewingHistoryRepository, ProfileRepository profileRepository, EpisodeRepository episodeRepository, MovieRepository movieRepository)
    {
        this.viewingHistoryRepository = viewingHistoryRepository;
        this.profileRepository = profileRepository;
        this.episodeRepository = episodeRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ViewingHistoryDto createViewingHistory(int profileId, ViewingHistoryDto viewingHistoryDto) throws ProfileNotFoundException
    {
        Profile profile = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("profile not found"));

        Episode episode = this.episodeRepository.findById(viewingHistoryDto.getEpisode().getEpisodeId())
                .orElseThrow(() -> new RuntimeException("Episode not found"));

        Movie movie = this.movieRepository.findById(viewingHistoryDto.getMovie().getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        ViewingHistory viewingHistory = mapToEntity(viewingHistoryDto);
        viewingHistory.setProfile(profile);
        viewingHistory.setWatchedPercentage(0);
        viewingHistory.setEpisode(episode);
        viewingHistory.setMovie(movie);
        viewingHistory.setViewedAt(LocalDateTime.now());
        ViewingHistory newViewingHistory = this.viewingHistoryRepository.save(viewingHistory);

        return mapToDto(newViewingHistory);
    }

    @Override
    public List<ViewingHistoryDto> getViewingHistoriesByProfileId(int profileId) throws ProfileNotFoundException, ViewingHistoryNotFoundException
    {
        List<ViewingHistory> viewingHistories = this.viewingHistoryRepository.findByProfileId(profileId);

        if (viewingHistories.isEmpty())
        {
            throw new ViewingHistoryNotFoundException("no viewing history");
        }

        return viewingHistories.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ViewingHistoryDto getViewingHistoryById(int historyId) throws ViewingHistoryNotFoundException
    {
        ViewingHistory viewingHistory = this.viewingHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ViewingHistoryNotFoundException("Viewing history not found"));

        return mapToDto(viewingHistory);
    }

    @Override
    public void deleteViewingHistory(int historyId) throws ViewingHistoryNotFoundException
    {
        ViewingHistory viewingHistory = this.viewingHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ViewingHistoryNotFoundException("Viewing history not found for delete"));
        this.viewingHistoryRepository.delete(viewingHistory);
    }

    @Override
    @Transactional
    public ViewingHistoryDto updateViewingHistory(int profileId, int vhId, ViewingHistoryInternalDto viewingHistoryDto) throws ProfileNotFoundException
    {
        // Check if ViewingHistory exists
        ViewingHistory existingViewingHistory = viewingHistoryRepository.findById(vhId)
                .orElseThrow(() -> new RuntimeException("ViewingHistory not found"));

        // Fetch associated entities (Profile, Movie, Episode)
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

        Movie movie = movieRepository.findById(viewingHistoryDto.getMovie().getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Episode episode = episodeRepository.findById(viewingHistoryDto.getEpisode().getEpisodeId())
                .orElseThrow(() -> new RuntimeException("Episode not found"));

        // Update fields
        existingViewingHistory.setProfile(profile);
        existingViewingHistory.setMovie(movie);
        existingViewingHistory.setEpisode(episode);
        existingViewingHistory.setWatchedPercentage(viewingHistoryDto.getWatchedPercentage());
        existingViewingHistory.setViewedAt(viewingHistoryDto.getViewedAt());
        existingViewingHistory.setStopAt(viewingHistoryDto.getStopAt());
        // Save updated ViewingHistory
        ViewingHistory updatedViewingHistory = viewingHistoryRepository.save(existingViewingHistory);

        // Convert to DTO for response
        return mapToDto(updatedViewingHistory);
    }

    private ViewingHistoryDto mapToDto(ViewingHistory viewingHistory)
    {
        ViewingHistoryDto viewingHistoryDto = new ViewingHistoryDto();
        viewingHistoryDto.setHistoryId(viewingHistory.getHistoryId());
        viewingHistoryDto.setMovie(viewingHistory.getMovie());
        return viewingHistoryDto;
    }

    private ViewingHistory mapToEntity(ViewingHistoryDto viewingHistoryDto)
    {
        ViewingHistory viewingHistory = new ViewingHistory();
        viewingHistory.setHistoryId(viewingHistoryDto.getHistoryId());

        return viewingHistory;
    }
}

