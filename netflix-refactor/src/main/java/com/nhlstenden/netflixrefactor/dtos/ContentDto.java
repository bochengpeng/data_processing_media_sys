package com.nhlstenden.netflixrefactor.dtos;

import com.nhlstenden.netflixrefactor.models.VideoQuality;
import com.nhlstenden.netflixrefactor.models.ViewingClassification;
import lombok.Data;

import java.util.Set;

@Data
public class ContentDto {
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer duration;
    private Set<String> genres;
    private Set<ViewingClassification> viewingClassifications;
    private Integer minimumAge;
    private Set<VideoQuality> availableQualities;
}

