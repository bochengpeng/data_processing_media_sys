package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.AgeRating;
import com.netflix.api.netflix.models.ContentClassification;
import com.netflix.api.netflix.models.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Data
public class ContentDto
{
    private int contentId;
    private String title;
    private String description;
    private Genre genres;
    private LocalDate releaseDate;
    private ContentClassification contentClassification;
    private String thumbnailUrl;
    private double rating;
    private AgeRating ageRating;

}
