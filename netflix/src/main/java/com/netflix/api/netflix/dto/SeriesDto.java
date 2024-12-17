package com.netflix.api.netflix.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeriesDto extends ContentDto
{
    private int totalSeasons;
    private List<EpisodeDto> episodes;
}
