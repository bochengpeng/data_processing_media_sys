package org.example.moviesystem.controller;

import org.example.moviesystem.model.TvSeries;
import org.example.moviesystem.repository.TvSeriesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TvSeriesController {

    private final TvSeriesRepository tvSeriesRepository;

    public TvSeriesController(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    @GetMapping("/tv-series")
    public List<TvSeries> getAllTvSeries() {
        return tvSeriesRepository.findAll();
    }
}