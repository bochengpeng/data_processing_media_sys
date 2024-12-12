package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.models.Series;
import com.netflix.api.netflix.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SeriesController {
    @Autowired
    private SeriesRepository seriesRepository;

    @GetMapping
    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Series> getSeriesById(@PathVariable int id) {
        return seriesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Series createSeries(@RequestBody Series series) {
        return seriesRepository.save(series);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Series> updateSeries(@PathVariable int id, @RequestBody Series seriesDetails) {
        return seriesRepository.findById(id)
                .map(series -> {
//                    series.setTitle(seriesDetails.getTitle());
                    return ResponseEntity.ok(seriesRepository.save(series));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable int id) {
        return seriesRepository.findById(id)
                .map(series -> {
                    seriesRepository.delete(series);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}