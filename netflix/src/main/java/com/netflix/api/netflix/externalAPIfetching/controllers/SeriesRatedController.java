package com.netflix.api.netflix.externalAPIfetching.controllers;

import com.netflix.api.netflix.externalAPIfetching.models.RatedSeries;
import com.netflix.api.netflix.externalAPIfetching.services.SeriesRatedService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SeriesRatedController
{
    private final SeriesRatedService SeriesRatedService;

    public SeriesRatedController(SeriesRatedService SeriesRatedService)
    {
        this.SeriesRatedService = SeriesRatedService;
    }

    @GetMapping("/home")
    public String getHomePage(HttpSession session)
    {
        return "home";
    }

    @GetMapping("/tv_rate")
    public String getTopRatedTVSeries(Model model)
    {
        List<RatedSeries> topRatedTVSeries = this.SeriesRatedService.getTopRatedTVSeries();
        System.out.println("TV RatedSeries Data: " + topRatedTVSeries);
        for (RatedSeries ratedSeries : topRatedTVSeries) {
            System.out.println("TV RatedSeries Rating: " + ratedSeries.getVoteAverage());
            System.out.println("Poster Path: " + ratedSeries.getPosterPath());
        }

        model.addAttribute("ratedSeries", topRatedTVSeries);

        return "ratedSeries";
    }
}
