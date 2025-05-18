package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.Episode;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Data
public class SeriesDto
{

//    @Min(value = 1, message = "Series ID must be greater than 0")
    private int seriesId;

    @Min(value = 1, message = "Total seasons must be at least 1")
    private int totalSeasons;

    @NotBlank(message = "Current season name is required")
    @Size(max = 100, message = "Season name must be less than 100 characters")
    private String currentSeason;

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must be less than 150 characters")
    private String title;

    @Valid
    private List<Episode> episodes;
}

