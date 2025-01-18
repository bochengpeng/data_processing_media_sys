package com.netflix.api.netflix.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class TMDBMovieDTO
{
    private int id;
    private String title;
    private int runtime;
    private String overview;
    private String release_date;
    private List<GenreDto> genres; // Genre information
    private List<ReleaseDateDto> release_dates; // Certification details
}

