package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.EpisodeDto;
import com.netflix.api.netflix.exception.EpisodeNotFoundException;
import com.netflix.api.netflix.services.EpisodeService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/episodes")
@RequiredArgsConstructor
@Slf4j
public class EpisodeController
{
    private final EpisodeService episodeService;

    // Create a new Episode
    @PostMapping("/create")
    public ResponseEntity<?> createEpisode(@RequestBody @Valid EpisodeDto episodeDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            EpisodeDto createdEpisode = this.episodeService.createEpisode(episodeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEpisode);
        } catch (ValidationException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            log.error("Failed to create episode: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the episode.");
        }
    }

    // Get all Episodes
    @GetMapping("/view-all")
    public ResponseEntity<List<EpisodeDto>> getAllEpisodes()
    {
        List<EpisodeDto> episodes = this.episodeService.getAllEpisodes();
        return ResponseEntity.ok(episodes);
    }

    // Get Episode by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEpisodeById(@PathVariable int id)
    {
        try
        {
            if (id <= 0) throw new ValidationException("ID must be positive.");
            EpisodeDto episode = this.episodeService.getEpisodeById(id);
            return ResponseEntity.ok(episode);
        } catch (EpisodeNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Episode not found.");
        } catch (ValidationException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            log.error("Error fetching episode by id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching episode details.");
        }
    }

    // Update an Episode
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEpisode(@PathVariable int id, @RequestBody @Valid EpisodeDto episodeDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            if (id <= 0) throw new ValidationException("ID must be positive.");
            EpisodeDto updatedEpisode = this.episodeService.updateEpisode(id, episodeDto);
            return ResponseEntity.ok(updatedEpisode);
        } catch (EpisodeNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Episode not found.");
        } catch (ValidationException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            log.error("Failed to update episode {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the episode.");
        }
    }

    // Delete an Episode
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEpisode(@PathVariable int id)
    {
        try
        {
            if (id <= 0) throw new ValidationException("ID must be positive.");
            this.episodeService.deleteEpisode(id);

            return ResponseEntity.noContent().build();
        } catch (EpisodeNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Episode not found.");
        } catch (ValidationException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            log.error("Error deleting episode {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete episode.");
        }
    }
}
