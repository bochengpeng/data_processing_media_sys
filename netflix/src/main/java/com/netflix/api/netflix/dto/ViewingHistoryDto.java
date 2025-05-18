package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.VideoQuality;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

@Getter
@Setter
public class ViewingHistoryDto
{
    private Integer profileId;

    // Either movieId or episodeId must be provided;
    private Integer movieId;
    private Integer episodeId;

    @NotNull(message = "Viewed time is required")
    private LocalDateTime viewedAt;

    private LocalDateTime stopAt;

    @Min(value = 0, message = "Watch duration cannot be negative")
    private Integer watchDuration;

    @Min(value = 0, message = "Resume position cannot be negative")
    private Integer resumePosition;

    private boolean completed;

    private boolean subtitlesEnabled;

    @NotNull(message = "Video quality is required")
    private VideoQuality selectedQuality;
}


