package org.example.moviesystem.controller;

import jakarta.servlet.http.HttpSession;
import org.example.moviesystem.model.PopularSeries;
import org.example.moviesystem.service.SeriesPopularService;
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
        return seriesPopularService.getTopPopularTVSeries();
    }
}
