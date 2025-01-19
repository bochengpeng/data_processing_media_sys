package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.Series;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EpisodeDto
{
    private int episodeId;
    private String title;
    private int seasonNumber;
    private int episodeNumber;
    private int duration;
    private LocalDate releaseDate;
    private String description;
    private Series series;
}
