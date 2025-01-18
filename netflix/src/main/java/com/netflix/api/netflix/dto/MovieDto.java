package com.netflix.api.netflix.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDto
{
    private int movieId;
    private String title;
    private int duration; // Duration in mins
    private short ageRating; // Use short to match SMALLINT
    private short contentClassification;
    private short genre;
    private String description;
    private LocalDate releaseDate;
}
