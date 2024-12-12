package org.example.moviesystem.controller;

import org.example.moviesystem.model.Series;
import org.example.moviesystem.service.TVSeriesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TVSeriesRestController
{

    private final TVSeriesService TVSeriesService;

    public TVSeriesRestController(TVSeriesService TVSeriesService) {
        this.TVSeriesService = TVSeriesService;
    }

    @GetMapping("/series")
    public List<Series> getTopRatedSeries() {
        return TVSeriesService.getTopRatedTVSeries();
    }
}
