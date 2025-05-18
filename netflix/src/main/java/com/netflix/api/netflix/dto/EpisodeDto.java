package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.Series;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EpisodeDto
{
//    @Min(value = 1, message = "Episode ID must be at least 1")
    private int episodeId;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @Min(value = 1, message = "Season number must be at least 1")
    private int seasonNumber;

    @Min(value = 1, message = "Episode number must be at least 1")
    private int episodeNumber;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int duration;

    @NotNull(message = "Release date is required")
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    @NotBlank(message = "Description is required")
    @Size(max = 250, message = "Description must be less than 250 characters")
    private String description;

    @Valid
    @NotNull(message = "Series is required")
    private Series series;
}