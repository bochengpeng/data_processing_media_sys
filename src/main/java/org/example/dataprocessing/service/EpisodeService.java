package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.Episode;
import org.example.dataprocessing.repository.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpisodeService
{

    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository)
    {
        this.episodeRepository = episodeRepository;
    }

    // Create a new episode
    public Episode createEpisode(Episode episode)
    {
        return episodeRepository.save(episode);
    }

    // Get an episode by its ID
    public Optional<Episode> getEpisodeById(Long episodeId)
    {
        return episodeRepository.findById(episodeId);
    }

    // Fetch episodes by Series ID
    public List<Episode> getEpisodesBySerieId(Long serieId)
    {
        return episodeRepository.findBySerie_SerieId(serieId);
    }

    // Fetch episodes by Series ID and Season Number
    public List<Episode> getEpisodesBySeason(Long serieId, int seasonNumber)
    {
        return episodeRepository.findBySerie_SerieIdAndSeasonNumber(serieId, seasonNumber);
    }

    // Update an existing episode
    public Episode updateEpisode(Long episodeId, Episode updatedEpisode)
    {
        return episodeRepository.findById(episodeId).map(existingEpisode ->
        {
            existingEpisode.setTitle(updatedEpisode.getTitle());
            existingEpisode.setDescription(updatedEpisode.getDescription());
            existingEpisode.setGenres(updatedEpisode.getGenres());
            existingEpisode.setAgeRating(updatedEpisode.getAgeRating());
            existingEpisode.setSupportedQualities(updatedEpisode.getSupportedQualities());
            existingEpisode.setEpisodeNumber(updatedEpisode.getEpisodeNumber());
            existingEpisode.setSeasonNumber(updatedEpisode.getSeasonNumber());
            existingEpisode.setDuration(updatedEpisode.getDuration());
            return episodeRepository.save(existingEpisode);
        }).orElseThrow(() -> new IllegalArgumentException("Episode not found with ID: " + episodeId));
    }

    // Delete an episode by its ID
    public void deleteEpisode(Long episodeId)
    {
        if (!episodeRepository.existsById(episodeId))
        {
            throw new IllegalArgumentException("Episode not found with ID: " + episodeId);
        }
        episodeRepository.deleteById(episodeId);
    }
}
