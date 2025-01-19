package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.EpisodeDto;
import com.netflix.api.netflix.exception.EpisodeNotFoundException;
import com.netflix.api.netflix.models.Episode;

import java.util.List;

public interface EpisodeService
{
    EpisodeDto createEpisode(EpisodeDto episodeDto);
    List<EpisodeDto> getAllEpisodes();
    EpisodeDto getEpisodeById(int episodeId) throws EpisodeNotFoundException;
    EpisodeDto updateEpisode(int episodeId, EpisodeDto episodeDto) throws EpisodeNotFoundException;
    void deleteEpisode(int episodeId) throws EpisodeNotFoundException;
}
