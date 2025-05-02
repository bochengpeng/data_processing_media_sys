package com.nhlstenden.netflixrefactor.externalApiFetching.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PopularSeries
{
    private String name;
    @JsonProperty("poster_path")
    private String posterPath;
    private String overview;
    @JsonProperty("origin_country")
    private List<String> originCountry; // Changed from String to List<String>
    private double popularity;
}
