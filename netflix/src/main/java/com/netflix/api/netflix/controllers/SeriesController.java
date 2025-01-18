package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.MovieDto;
import com.netflix.api.netflix.dto.SeriesDto;
import com.netflix.api.netflix.models.Movie;
import com.netflix.api.netflix.models.Series;
import com.netflix.api.netflix.repository.SeriesRepository;
import com.netflix.api.netflix.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/series")
public class SeriesController
{
    @Autowired
    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService)
    {
        this.seriesService = seriesService;
    }

    @GetMapping("/allSeries")
    public ResponseEntity<List<SeriesDto>> getAllSeries()
    {
        List<SeriesDto> series = this.seriesService.getAllSeries();
        return ResponseEntity.ok(series);
    }

    @GetMapping(
            value = "/{id}/details",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<SeriesDto> getSeriesDetails(
            @PathVariable("id") int id,
            @RequestParam(value = "format", required = false) String format)
    {
        SeriesDto seriesDto = this.seriesService.getSeriesById(id);
        return ResponseEntity.ok(seriesDto);
    }
}