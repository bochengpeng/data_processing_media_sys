package org.example.moviesystem.service;

import org.example.moviesystem.model.RatedSeries;
import org.example.moviesystem.response.RatedResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SeriesRatedService
{
    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public SeriesRatedService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Fetch top-rated TV series from TMDB
    public List<RatedSeries> getTopRatedTVSeries() {
        String url = apiUrl + "/tv/top_rated?api_key=" + apiKey + "&language=en-US&page=1"; // Fetch 20 top-rated TV series from page 1
        RatedResponse response = restTemplate.getForObject(url, RatedResponse.class);
        return response != null ? Arrays.asList(response.getResults()) : List.of();
    }
}
//The SeriesRatedService class interacts with the TMDB API to retrieve top-rated series.