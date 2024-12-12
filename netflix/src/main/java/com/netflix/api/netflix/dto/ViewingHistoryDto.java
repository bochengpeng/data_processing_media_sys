package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.ViewingSession;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ViewingHistoryDto {
    private Long historyId;
    private ContentDto content;
    private LocalDateTime viewedAt;
    private LocalDateTime stopAt;
    private double watchedPercentage;

    public ViewingHistoryDto(int historyId, List<ViewingSession> watchedList, LocalDateTime stopAt)
    {
    }
}
