package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.ViewingHistoryDto;
import com.netflix.api.netflix.exception.ViewingHistoryNotFoundException;
import com.netflix.api.netflix.models.Episode;
import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.models.Profile;
import com.netflix.api.netflix.models.ViewingHistory;
import com.netflix.api.netflix.repositories.EpisodeRepository;
import com.netflix.api.netflix.repositories.MovieRepository;
import com.netflix.api.netflix.repositories.ProfileRepository;
import com.netflix.api.netflix.repositories.ViewingHistoryRepository;
import com.netflix.api.netflix.services.ViewingHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ViewingHistoryServiceImpl implements ViewingHistoryService
{

    private final ViewingHistoryRepository viewingHistoryRepository;
    private final MovieRepository movieRepository;
    private final EpisodeRepository episodeRepository;
    private final ProfileRepository profileRepository;

    @Override
    public ViewingHistoryDto createViewingHistory(ViewingHistoryDto dto)
    {
        try
        {
            ViewingHistory entity = mapToEntity(dto);
            return mapToDto(this.viewingHistoryRepository.save(entity));
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create viewing history", e);
        }
    }

    @Override
    public ViewingHistoryDto updateViewingHistory(int id, ViewingHistoryDto dto)
    {
        try
        {
            ViewingHistory existing = this.viewingHistoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ViewingHistory not found"));

            ViewingHistory updated = mapToEntity(dto);
            updated.setHistoryId(id);

            return mapToDto(this.viewingHistoryRepository.save(updated));
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to update viewing history", e);
        }
    }

    @Override
    public void deleteViewingHistory(int id)
    {
        try
        {
            this.viewingHistoryRepository.deleteById(id);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to delete viewing history", e);
        }
    }

    @Override
    public ViewingHistoryDto getViewingHistoryById(int id)
    {
        try
        {
            return this.viewingHistoryRepository.findById(id)
                    .map(this::mapToDto)
                    .orElseThrow(() -> new RuntimeException("ViewingHistory not found"));
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to retrieve viewing history", e);
        }
    }

    @Override
    public List<ViewingHistoryDto> getAllViewingHistories()
    {
        try
        {
            return this.viewingHistoryRepository.findAll()
                    .stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to retrieve viewing histories", e);
        }
    }

    @Override
    public List<ViewingHistoryDto> getViewingHistoriesByProfileId(int profileId) throws ViewingHistoryNotFoundException
    {
        try
        {
            List<ViewingHistory> viewingHistories = this.viewingHistoryRepository.findByProfileId(profileId);

            if (viewingHistories.isEmpty())
            {
                throw new ViewingHistoryNotFoundException("No viewing history for the given profile");
            }

            return viewingHistories.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (ViewingHistoryNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to retrieve viewing histories by profile ID", e);
        }
    }

    // DTO to Entity
    private ViewingHistory mapToEntity(ViewingHistoryDto dto)
    {
        ViewingHistory vh = new ViewingHistory();
        try
        {
            if (dto.getProfileId() != null)
            {
                Profile profile = this.profileRepository.findById(dto.getProfileId())
                        .orElseThrow(() -> new RuntimeException("Profile not found"));
                vh.setProfile(profile);
            }

            if (dto.getMovieId() != null)
            {
                Movie movie = this.movieRepository.findById(dto.getMovieId())
                        .orElseThrow(() -> new RuntimeException("Movie not found"));
                vh.setMovie(movie);
            }

            if (dto.getEpisodeId() != null)
            {
                Episode episode = this.episodeRepository.findById(dto.getEpisodeId())
                        .orElseThrow(() -> new RuntimeException("Episode not found"));
                vh.setEpisode(episode);
            }

            vh.setViewedAt(dto.getViewedAt());
            vh.setStopAt(dto.getStopAt());
            vh.setWatchDuration(dto.getWatchDuration());
            vh.setResumePosition(dto.getResumePosition());
            vh.setCompleted(dto.isCompleted());
            vh.setSubtitlesEnabled(dto.isSubtitlesEnabled());
            vh.setSelectedQuality(dto.getSelectedQuality());

        } catch (Exception e)
        {
            throw new RuntimeException("Failed to map ViewingHistoryDto to entity", e);
        }
        return vh;
    }

    // Entity to DTO
    private ViewingHistoryDto mapToDto(ViewingHistory vh)
    {
        ViewingHistoryDto dto = new ViewingHistoryDto();
        try
        {
            dto.setMovieId(vh.getMovie() != null ? vh.getMovie().getId() : null);
            dto.setEpisodeId(vh.getEpisode() != null ? vh.getEpisode().getEpisodeId() : null);
            dto.setProfileId(vh.getProfile() != null ? vh.getProfile().getProfileId() : null);
            dto.setViewedAt(vh.getViewedAt());
            dto.setStopAt(vh.getStopAt());
            dto.setWatchDuration(vh.getWatchDuration());
            dto.setResumePosition(vh.getResumePosition());
            dto.setCompleted(vh.isCompleted());
            dto.setSubtitlesEnabled(vh.isSubtitlesEnabled());
            dto.setSelectedQuality(vh.getSelectedQuality());
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to map ViewingHistory to DTO", e);
        }
        return dto;
    }
}