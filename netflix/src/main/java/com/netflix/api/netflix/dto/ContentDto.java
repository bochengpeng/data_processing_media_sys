package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.ContentClassification;
import com.netflix.api.netflix.models.Genre;

import java.time.LocalDate;
import java.util.List;

public class ContentDto
{
    private Long contentId;
    private String title;
    private String description;
    private List<Genre> genres;
    private LocalDate releaseDate;
    private ContentClassification contentClassification;
    private String thumbnailUrl;
    private double rating;
}
