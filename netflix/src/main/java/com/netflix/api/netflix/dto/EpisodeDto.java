package com.netflix.api.netflix.dto;

import lombok.Data;

@Data
public class EpisodeDto {
    private Long episodeId;
    private String title;
    private int seasonNumber;
    private int episodeNumber;
    private int duration;
    private String description;
}
