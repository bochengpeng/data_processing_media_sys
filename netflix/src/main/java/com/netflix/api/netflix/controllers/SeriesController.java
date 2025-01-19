package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.SeriesDto;
import com.netflix.api.netflix.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        return new ResponseEntity<>(series, HttpStatus.OK);
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

        return new ResponseEntity<>(seriesDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SeriesDto> createSeries(@RequestBody SeriesDto seriesDto)
    {
        return new ResponseEntity<>(this.seriesService.createSeries(seriesDto), HttpStatus.CREATED);
    }

    @PutMapping("/{seriesId}/update")
    public ResponseEntity<SeriesDto> updateSeries(@RequestBody SeriesDto seriesDto, @PathVariable(value = "seriesId") int seriesId)
    {
        SeriesDto updatedSeries = this.seriesService.updateSeries(seriesId, seriesDto);
        return new ResponseEntity<>(updatedSeries, HttpStatus.OK);
    }

    @DeleteMapping("/{seriesId}/delete")
    public ResponseEntity<String> deleteSeries(@PathVariable(value = "seriesId") int seriesId)
    {
        this.seriesService.deleteSeries(seriesId);
        return new ResponseEntity<>("Series deleted successfully", HttpStatus.OK);
    }
}