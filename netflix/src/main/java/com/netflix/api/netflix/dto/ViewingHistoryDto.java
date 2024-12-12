package com.netflix.api.netflix.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewingHistoryDto {
    private Long historyId;
    private ContentDto content;
    private LocalDateTime viewedAt;
    private LocalDateTime stopAt;
    private double watchedPercentage;
}
