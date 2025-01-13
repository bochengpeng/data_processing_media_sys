package com.netflix.api.netflix.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RatedSeries
{
    // Getters and Setters
    private String name;
    @JsonProperty("vote_average")
    private double voteAverage; // This corresponds to the "vote_average" in the API response
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;

}