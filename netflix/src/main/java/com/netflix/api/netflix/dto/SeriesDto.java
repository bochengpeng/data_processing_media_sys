package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.Episode;
import lombok.Data;

import java.util.List;

@Data
public class SeriesDto
{
    private int seriesId;
    private int totalSeasons;
    private String currentSeason;
    private List<Episode> episodes;
}
