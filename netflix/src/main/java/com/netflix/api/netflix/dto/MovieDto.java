package com.netflix.api.netflix.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MovieDto
{
    private int movieId;

//    @Column(name = "duration", nullable = false, columnDefinition = "TIME DEFAULT '00:00:00'")
//    private LocalTime duration = LocalTime.of(0, 0, 0);

    private String title;
//    private int duration;
    private int runtime;
//    private String trailerUrl;
    private short ageRating; // Use short to match SMALLINT
    private short contentClassification;
    private short genre;

    private String description;

    private LocalDate releaseDate;
}
