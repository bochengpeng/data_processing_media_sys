package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.EpisodeDto;
import com.netflix.api.netflix.exception.EpisodeNotFoundException;
import com.netflix.api.netflix.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/episodes")
public class EpisodeController
{
    private final EpisodeService episodeService;

    @Autowired
    public EpisodeController(EpisodeService episodeService)
    {
        this.episodeService = episodeService;
    }

    // Create a new Episode
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EpisodeDto> createEpisode(@RequestBody EpisodeDto episodeDto)
    {
        EpisodeDto createdEpisode = episodeService.createEpisode(episodeDto);
        return new ResponseEntity<>(createdEpisode, HttpStatus.CREATED);
    }

    // Get all Episodes
    @GetMapping("/allEpisodes")
    public ResponseEntity<List<EpisodeDto>> getAllEpisodes()
    {
        List<EpisodeDto> episodes = this.episodeService.getAllEpisodes();
        return new ResponseEntity<>(episodes, HttpStatus.OK);
    }

    // Get Episode by ID
    @GetMapping("/{id}")
    public ResponseEntity<EpisodeDto> getEpisodeById(@PathVariable int id) throws EpisodeNotFoundException, EpisodeNotFoundException
    {
        EpisodeDto episode = episodeService.getEpisodeById(id);
        return new ResponseEntity<>(episode, HttpStatus.OK);
    }

    // Update an Episode
    @PutMapping("/update/{id}")
    public ResponseEntity<EpisodeDto> updateEpisode(@PathVariable int id, @RequestBody EpisodeDto episodeDto) throws EpisodeNotFoundException
    {
        EpisodeDto updatedEpisode = episodeService.updateEpisode(id, episodeDto);
        return new ResponseEntity<>(updatedEpisode, HttpStatus.OK);
    }

    // Delete an Episode
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable int id) throws EpisodeNotFoundException
    {
        episodeService.deleteEpisode(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
