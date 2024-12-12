package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.models.Episode;
import com.netflix.api.netflix.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {
    @Autowired
    private EpisodeRepository episodeRepository;

    @GetMapping
    public List<Episode> getAllEpisodes() {
        return episodeRepository.findAll();
    }

    @GetMapping("/{episodeId}")
    public ResponseEntity<Episode> getEpisodeById(@PathVariable int episodeId) {
        return episodeRepository.findById(episodeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Episode createEpisode(@RequestBody Episode episode) {
        return episodeRepository.save(episode);
    }

    @PutMapping("/{episodeId}")
    public ResponseEntity<Episode> updateEpisode(@PathVariable int episodeId, @RequestBody Episode episodeDetails) {
        return episodeRepository.findById(episodeId)
                .map(episode -> {
//                    episode.setTitle(episodeDetails.getTitle());
                    episode.setSeasonNumber(episodeDetails.getSeasonNumber());
                    return ResponseEntity.ok(episodeRepository.save(episode));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{episodeId}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable int episodeId) {
        return episodeRepository.findById(episodeId)
                .map(episode -> {
                    episodeRepository.delete(episode);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}