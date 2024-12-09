package org.example.moviesystem.service;

import org.example.moviesystem.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class MovieService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Movie> getTopRatedMovies() {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl + "/movie/top_rated")
                .queryParam("api_key", apiKey)
                .queryParam("language", "en-US")
                .queryParam("page", 1) // You can change the page if needed
                .toUriString();   // Convert the builder to a URI string

        MovieResponse response = restTemplate.getForObject(url, MovieResponse.class);
        //Sends an HTTP GET request to the constructed URL and maps the response into a MovieResponse object.
        System.out.println("API Response: " + response);

        return response != null ? response.getResults() : null;
    }
}
//The MovieService class interacts with the TMDB API to retrieve top-rated movies.