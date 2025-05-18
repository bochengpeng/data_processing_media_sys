package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchListDto
{

    @Min(value = 1, message = "Watchlist ID must be a positive integer")
    private int id;

    @NotNull(message = "Profile ID is required")
    @Min(value = 1, message = "Profile ID must be a positive integer")
    private Integer profileId;

    @NotNull(message = "Content ID is required")
    @Min(value = 1, message = "Content ID must be a positive integer")
    private Integer contentId;

    @NotNull(message = "Content type is required")
    private ContentType contentType;

    @NotNull(message = "Added date and time is required")
    private LocalDateTime addedAt;
}


