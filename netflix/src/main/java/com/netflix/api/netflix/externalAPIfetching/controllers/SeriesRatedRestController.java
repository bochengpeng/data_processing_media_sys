package com.netflix.api.netflix.externalAPIfetching.controllers;

import com.netflix.api.netflix.externalAPIfetching.models.RatedSeries;
import com.netflix.api.netflix.externalAPIfetching.services.SeriesRatedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import java.util.List;

@RestController
public class SeriesRatedRestController
{
    private final SeriesRatedService seriesRatedService;

    public SeriesRatedRestController(SeriesRatedService seriesRatedService)
    {
        this.seriesRatedService = seriesRatedService;
    }

    @GetMapping("/series")
    public List<RatedSeries> getTopRatedSeries()
    {
        return this.seriesRatedService.getTopRatedTVSeries();
    }

    @GetMapping("/ratedxml")
    public Document getTopRatedSeriesXML()
    {
        return this.seriesRatedService.getTopRatedTVSeriesAsXml();
    }
}
