package org.example.moviesystem.controller;

import org.example.moviesystem.model.RatedSeries;
import org.example.moviesystem.service.SeriesRatedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SeriesRatedController
{
    private final SeriesRatedService SeriesRatedService;

    public SeriesRatedController(SeriesRatedService SeriesRatedService) {
        this.SeriesRatedService = SeriesRatedService;
    }

    @GetMapping("/home")
    public String getHomePage()
    {
        return "home";
    }

    @GetMapping("/tv_rate")
    public String getTopRatedTVSeries(Model model) {
        List<RatedSeries> topRatedTVSeries = SeriesRatedService.getTopRatedTVSeries();
        System.out.println("TV RatedSeries Data: " + topRatedTVSeries);
        for (RatedSeries ratedSeries : topRatedTVSeries) {
            System.out.println("TV RatedSeries Rating: " + ratedSeries.getVoteAverage());
            System.out.println("Poster Path: " + ratedSeries.getPosterPath());
        }

        model.addAttribute("ratedSeries", topRatedTVSeries);

        return "ratedSeries";
    }
}
