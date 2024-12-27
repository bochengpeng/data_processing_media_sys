package org.example.moviesystem.controller;

import org.example.moviesystem.model.PopularSeries;
import org.example.moviesystem.service.SeriesPopularService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SeriesPopularController
{
    private final SeriesPopularService seriesPopularService;

    public SeriesPopularController(SeriesPopularService seriesPopularService)
    {
        this.seriesPopularService = seriesPopularService;
    }

    @GetMapping("/popular")
    public String getTopPopularTVSeries(Model model)
    {
        List<PopularSeries> topPopularTVSeries = seriesPopularService.getTopPopularTVSeries();
        System.out.println("Top popular TV series: " + topPopularTVSeries);
        for (PopularSeries popularSeries : topPopularTVSeries)
        {
            System.out.println("TV Popularity: " + popularSeries.getPopularity());
            System.out.println("Poster Path: " + popularSeries.getPosterPath());
            System.out.println("Origin Country: " + popularSeries.getOriginCountry());
        }

        model.addAttribute("popularSeries", topPopularTVSeries);

        return "popularSeries"; //refers to the name of the HTML file
    }
}
