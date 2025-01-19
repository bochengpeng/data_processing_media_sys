package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.EpisodeDto;
import com.netflix.api.netflix.exception.EpisodeNotFoundException;
import com.netflix.api.netflix.models.*;
import com.netflix.api.netflix.repository.EpisodeRepository;
import com.netflix.api.netflix.repository.SeriesRepository;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    // Create a new Episode
    public EpisodeDto createEpisode(EpisodeDto episodeDto)
    {
        Series series = this.seriesRepository.findById(episodeDto.getSeries().getSeriesId())
                .orElseThrow(() -> new RuntimeException("Series not found"));

        Episode episode = mapToEntity(episodeDto);
        episode.setSeries(series);
        Episode savedEpisode = this.episodeRepository.save(episode);

        return mapToDto(savedEpisode);
    }

    @Override
    // Get all Episodes
    public List<EpisodeDto> getAllEpisodes()
    {
        List<Episode> episodes = this.episodeRepository.findAll();

        return episodes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    // Get Episode by ID
    public EpisodeDto getEpisodeById(int episodeId) throws EpisodeNotFoundException
    {
        Episode episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> new EpisodeNotFoundException("Episode not found"));

        // Convert entity to DTO and return
        return mapToDto(episode);
    }

    @Override
    // Update an Episode
    public EpisodeDto updateEpisode(int episodeId, EpisodeDto episodeDto) throws EpisodeNotFoundException
    {
        Episode existingEpisode = this.episodeRepository.findById(episodeId)
                .orElseThrow(() -> new EpisodeNotFoundException("Episode not found"));

        existingEpisode.setTitle(episodeDto.getTitle());
        existingEpisode.setDescription(episodeDto.getDescription());
        existingEpisode.setReleaseDate(episodeDto.getReleaseDate());
        existingEpisode.setSeries(episodeDto.getSeries()); // Assuming you set the movie object in the DTO

        Episode updatedEpisode = this.episodeRepository.save(existingEpisode);

        return mapToDto(updatedEpisode);
    }

    @Override
    // Delete an Episode
    public void deleteEpisode(int episodeId) throws EpisodeNotFoundException
    {
        Episode existingEpisode = this.episodeRepository.findById(episodeId)
                .orElseThrow(() -> new EpisodeNotFoundException("Episode not found"));

        this.episodeRepository.delete(existingEpisode);
    }

    private EpisodeDto mapToDto(Episode episode)
    {
        EpisodeDto episodeDto = new EpisodeDto();
        episodeDto.setEpisodeId(episode.getEpisodeId());
        episodeDto.setTitle(episode.getTitle());
        episodeDto.setReleaseDate(episode.getReleaseDate());
        episodeDto.setEpisodeNumber(episode.getEpisodeNumber());
        episodeDto.setDescription(episode.getDescription());
        episodeDto.setSeries(episode.getSeries());

        return episodeDto;
    }

    private Episode mapToEntity(EpisodeDto episodeDto)
    {
        Episode episode = new Episode();
        episode.setTitle(episodeDto.getTitle());
        episode.setDescription(episodeDto.getDescription());
        episode.setReleaseDate(episodeDto.getReleaseDate());
        episode.setSeries(episodeDto.getSeries());

        return episode;
    }
}
