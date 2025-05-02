package com.nhlstenden.netflixrefactor.externalApiFetching.controllers;

import com.nhlstenden.netflixrefactor.externalApiFetching.entities.RatedSeries;
import com.nhlstenden.netflixrefactor.externalApiFetching.services.SeriesRatedService;
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
        return seriesRatedService.getTopRatedTVSeries();
    }

    @GetMapping("/ratedxml")
    public Document getTopRatedSeriesXML()
    {
        return seriesRatedService.getTopRatedTVSeriesAsXml();
    }
}
