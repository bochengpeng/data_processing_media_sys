package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.Language;
import lombok.Data;

@Data
public class ProfileDto
{
    private int profileId;
    private String name;
    private int age;
    private String profilePhotoUrl;
    private Language language;
    private WatchListDto watchList;
    private ViewingHistoryDto viewingHistory;
}
