package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.Episode;
import com.netflix.api.netflix.models.Movie;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ViewingHistoryInternalDto {
    private int profileId;
    private Movie movie;
    private Episode episode;
    private double watchedPercentage;
    private LocalDateTime viewedAt;
    private LocalDateTime stopAt;
}

