package com.netflix.api.netflix.externalAPIfetching.controllers;

import com.netflix.api.netflix.externalAPIfetching.models.PopularSeries;
import com.netflix.api.netflix.externalAPIfetching.services.SeriesPopularService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeriesPopularRestController
{
    private final SeriesPopularService seriesPopularService;

    public SeriesPopularRestController(SeriesPopularService seriesPopularService)
    {
        this.seriesPopularService = seriesPopularService;
    }

    @GetMapping("/popular_series")
    public List<PopularSeries> getTopPopularSeries(HttpSession session)
    {
        return this.seriesPopularService.getTopPopularTVSeries();
    }
}
