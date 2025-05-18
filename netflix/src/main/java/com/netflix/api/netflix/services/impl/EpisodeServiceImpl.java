package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.EpisodeDto;
import com.netflix.api.netflix.exception.EpisodeNotFoundException;
import com.netflix.api.netflix.models.*;
import com.netflix.api.netflix.repositories.EpisodeRepository;
import com.netflix.api.netflix.repositories.SeriesRepository;
import com.netflix.api.netflix.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService
{

    private final EpisodeRepository episodeRepository;
    private final SeriesRepository seriesRepository;

    @Autowired
    public EpisodeServiceImpl(EpisodeRepository episodeRepository, SeriesRepository seriesRepository)
    {
        this.episodeRepository = episodeRepository;
        this.seriesRepository = seriesRepository;
    }

    @Transactional
    @Override
    public EpisodeDto createEpisode(EpisodeDto episodeDto)
    {
        try
        {
            Series series = this.seriesRepository.findById(episodeDto.getSeries().getId())
                    .orElseThrow(() -> new RuntimeException("Series not found"));

            Episode episode = mapToEntity(episodeDto);
            episode.setSeries(series);
            Episode savedEpisode = this.episodeRepository.save(episode);

            return mapToDto(savedEpisode);

        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create episode: " + e.getMessage(), e);
        }
    }

    @Override
    public List<EpisodeDto> getAllEpisodes()
    {
        try
        {
            List<Episode> episodes = this.episodeRepository.findAll();

            return episodes.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());

        } catch (Exception e)
        {
            throw new RuntimeException("Failed to fetch episodes: " + e.getMessage(), e);
        }
    }

    @Override
    public EpisodeDto getEpisodeById(int episodeId) throws EpisodeNotFoundException
    {
        try
        {
            Episode episode = episodeRepository.findById(episodeId)
                    .orElseThrow(() -> new EpisodeNotFoundException("Episode not found"));

            return mapToDto(episode);

        } catch (EpisodeNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to get episode by id: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public EpisodeDto updateEpisode(int episodeId, EpisodeDto episodeDto) throws EpisodeNotFoundException
    {
        try
        {
            Episode existingEpisode = this.episodeRepository.findById(episodeId)
                    .orElseThrow(() -> new EpisodeNotFoundException("Episode not found"));

            existingEpisode.setTitle(episodeDto.getTitle());
            existingEpisode.setDescription(episodeDto.getDescription());
            existingEpisode.setReleaseDate(episodeDto.getReleaseDate());
            existingEpisode.setEpisodeNumber(episodeDto.getEpisodeNumber());
            existingEpisode.setSeasonNumber(episodeDto.getSeasonNumber());
            existingEpisode.setDuration(episodeDto.getDuration());

            // Fetch series again from DB to avoid detached entity issues
            Series series = this.seriesRepository.findById(episodeDto.getSeries().getId())
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            existingEpisode.setSeries(series);

            Episode updatedEpisode = this.episodeRepository.save(existingEpisode);

            return mapToDto(updatedEpisode);

        } catch (EpisodeNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to update episode: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void deleteEpisode(int episodeId) throws EpisodeNotFoundException
    {
        try
        {
            Episode existingEpisode = this.episodeRepository.findById(episodeId)
                    .orElseThrow(() -> new EpisodeNotFoundException("Episode not found"));

            this.episodeRepository.delete(existingEpisode);

        } catch (EpisodeNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to delete episode: " + e.getMessage(), e);
        }
    }

    private EpisodeDto mapToDto(Episode episode)
    {
        EpisodeDto episodeDto = new EpisodeDto();
        episodeDto.setEpisodeId(episode.getEpisodeId());
        episodeDto.setTitle(episode.getTitle());
        episodeDto.setReleaseDate(episode.getReleaseDate());
        episodeDto.setEpisodeNumber(episode.getEpisodeNumber());
        episodeDto.setSeasonNumber(episode.getSeasonNumber());
        episodeDto.setDuration(episode.getDuration());
        episodeDto.setDescription(episode.getDescription());
//        episodeDto.setSeries(episode.getSeries());

        return episodeDto;
    }

    private Episode mapToEntity(EpisodeDto episodeDto)
    {
        Episode episode = new Episode();
        episode.setTitle(episodeDto.getTitle());
        episode.setDescription(episodeDto.getDescription());
        episode.setReleaseDate(episodeDto.getReleaseDate());
        episode.setEpisodeNumber(episodeDto.getEpisodeNumber());
        episode.setSeasonNumber(episodeDto.getSeasonNumber());
        episode.setDuration(episodeDto.getDuration());
        episode.setSeries(episodeDto.getSeries());

        return episode;
    }
}