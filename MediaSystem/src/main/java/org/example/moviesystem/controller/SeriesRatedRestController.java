package org.example.moviesystem.controller;

import org.example.moviesystem.model.RatedSeries;
import org.example.moviesystem.service.SeriesRatedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import java.util.List;

@RestController
public class SeriesRatedRestController
{
    private final SeriesRatedService seriesRatedService;

    public SeriesRatedRestController(SeriesRatedService seriesRatedService) {
        this.seriesRatedService = seriesRatedService;
    }

    @GetMapping("/series")
    public List<RatedSeries> getTopRatedSeries() {
        return seriesRatedService.getTopRatedTVSeries();
    }

    @GetMapping("/ratedxml")
    public Document getTopRatedSeriesXML()
    {
        return seriesRatedService.getTopRatedTVSeriesAsXml();
    }
}
