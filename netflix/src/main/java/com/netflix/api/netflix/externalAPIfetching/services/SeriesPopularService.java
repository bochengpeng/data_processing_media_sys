package com.netflix.api.netflix.externalAPIfetching.services;

import com.netflix.api.netflix.externalAPIfetching.models.PopularSeries;
import com.netflix.api.netflix.externalAPIfetching.response.PopularResponse;
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
        String url = this.apiUrl + "/tv/popular?api_key=" + this.apiKey + "&language=en-US&page=1"; // Fetch 20 top-rated TV series from page 1
        PopularResponse response = this.restTemplate.getForObject(url, PopularResponse.class);
        return response != null ? Arrays.asList(response.getPopularResults()) : List.of();
    }
}
