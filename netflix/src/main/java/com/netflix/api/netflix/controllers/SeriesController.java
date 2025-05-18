package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.SeriesDto;
import com.netflix.api.netflix.services.SeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/series")
@RequiredArgsConstructor
public class SeriesController
{

    private final SeriesService seriesService;

    // Get all series
    @GetMapping("view-all")
    public ResponseEntity<?> getAllSeries()
    {
        try
        {
            List<SeriesDto> series = this.seriesService.getAllSeries();
            return ResponseEntity.ok(series);
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve series: " + e.getMessage());
        }
    }

    // Get series by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSeriesById(@PathVariable int id)
    {
        try
        {
            SeriesDto seriesDto = this.seriesService.getSeriesById(id);
            return ResponseEntity.ok(seriesDto);
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Series not found with ID: " + id);
        }
    }

    // Create new series
    @PostMapping("/create")
    public ResponseEntity<?> createSeries(@Valid @RequestBody SeriesDto seriesDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation failed: " + result.getAllErrors());
        }

        try
        {
            SeriesDto created = this.seriesService.createSeries(seriesDto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create series: " + e.getMessage());
        }
    }

    // Update existing series
    @PutMapping("/update/{seriesId}")
    public ResponseEntity<?> updateSeries(@PathVariable int seriesId, @Valid @RequestBody SeriesDto seriesDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation failed: " + result.getAllErrors());
        }

        try
        {
            SeriesDto updated = this.seriesService.updateSeries(seriesId, seriesDto);
            return ResponseEntity.ok(updated);
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to update series: " + e.getMessage());
        }
    }

    // Delete series
    @DeleteMapping("/delete/{seriesId}")
    public ResponseEntity<String> deleteSeries(@PathVariable int seriesId)
    {
        try
        {
            this.seriesService.deleteSeries(seriesId);
            return ResponseEntity.ok("Series deleted successfully.");
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete series: " + e.getMessage());
        }
    }
}