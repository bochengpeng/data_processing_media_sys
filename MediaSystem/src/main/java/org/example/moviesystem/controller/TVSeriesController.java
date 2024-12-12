package org.example.moviesystem.controller;

import org.example.moviesystem.model.Series;
import org.example.moviesystem.service.TVSeriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TVSeriesController
{

    private final TVSeriesService TVSeriesService;

    public TVSeriesController(TVSeriesService TVSeriesService) {
        this.TVSeriesService = TVSeriesService;
    }

    @GetMapping("/tv_rate")
    public String getTopRatedTVSeries(Model model) {
        List<Series> topRatedTVSeries = TVSeriesService.getTopRatedTVSeries();
        System.out.println("TV Series Data: " + topRatedTVSeries);
        for (Series series : topRatedTVSeries) {
            System.out.println("TV Series Rating: " + series.getVoteAverage());
            System.out.println("Poster Path: " + series.getPosterPath());
        }

        model.addAttribute("series", topRatedTVSeries);

        return "index";
    }
}
