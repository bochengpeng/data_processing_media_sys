package org.example.moviesystem.service;

import org.example.moviesystem.model.PopularSeries;
import org.example.moviesystem.response.PopularResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SeriesPopularService
{
    @Value("${tmdb.api.key}")
    private String apiKey;
    @Value("${tmdb.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;

    public SeriesPopularService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    // Fetch top-rated TV series from TMDB
    public List<PopularSeries> getTopPopularTVSeries()
    {
        String url = apiUrl + "/tv/popular?api_key=" + apiKey + "&language=en-US&page=1"; // Fetch 20 top-rated TV series from page 1
        PopularResponse response = restTemplate.getForObject(url, PopularResponse.class);
        return response != null ? Arrays.asList(response.getPopularResults()) : List.of();
    }
}