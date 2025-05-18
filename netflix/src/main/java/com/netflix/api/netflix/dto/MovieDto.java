package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.AgeRating;
import com.netflix.api.netflix.models.ContentClassification;
import com.netflix.api.netflix.models.Genre;
import com.netflix.api.netflix.models.VideoQuality;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class MovieDto
{
    private int movieId;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    @NotNull(message = "Duration is required")
    private Integer duration;

    @NotNull(message = "Age rating is required")
    private AgeRating ageRating;

    @NotNull(message = "Content classification is required")
    @Size(min = 1, message = "At least one content classification is required")
    private Set<ContentClassification> contentClassification;

    @NotNull(message = "Genre is required")
    private Genre genre;

    @NotBlank(message = "Description is required")
    @Size(max = 5000, message = "Description must be less than 5000 characters")
    private String description;

    @NotNull(message = "Release date is required")
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    @NotNull(message = "Available qualities are required")
    @Size(min = 1, message = "At least one video quality must be specified")
    private Set<VideoQuality> availableQualities;

    @NotBlank(message = "Director name is required")
    @Size(max = 100, message = "Director name must be less than 100 characters")
    private String director;

    @NotNull(message = "Actors list is required")
    @Size(min = 1, message = "At least one actor is required")
    private Set<@NotBlank(message = "Actor name cannot be blank") String> actors;
}

