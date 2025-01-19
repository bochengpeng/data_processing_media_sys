package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.AgeRating;
import com.netflix.api.netflix.models.ContentClassification;
import com.netflix.api.netflix.models.Genre;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDto
{
    private int movieId;
    private String title;
    private int duration; // Duration in mins
    private AgeRating ageRating; // Use short to match SMALLINT
    private ContentClassification contentClassification;
    private Genre genre;
    private String description;
    private LocalDate releaseDate;
}
