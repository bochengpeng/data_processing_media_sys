package com.netflix.api.netflix.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDto
{
    private int movieId;

    private int duration;

    private String title;
//    private int duration;
//    private String trailerUrl;
private short ageRating; // Use short to match SMALLINT
    private short contentClassification;
    private short genre;

    private String description;

    private LocalDate releaseDate;
}
