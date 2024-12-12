package org.example.moviesystem.service;

import org.example.moviesystem.model.Series;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TVSeriesService
{

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public TVSeriesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Fetch top-rated TV series from TMDB
    public List<Series> getTopRatedTVSeries() {
        String url = apiUrl + "/tv/top_rated?api_key=" + apiKey + "&language=en-US&page=1"; // Fetch 20 top-rated TV series from page 1
        TMDBResponse response = restTemplate.getForObject(url, TMDBResponse.class);
        return response != null ? Arrays.asList(response.getResults()) : List.of();
    }
}
//The TVSeriesService class interacts with the TMDB API to retrieve top-rated movies.