package com.netflix.api.netflix.dto;

import lombok.Data;

import java.util.List;

@Data
public class WatchListDto {
    private int watchListId;
    private List<ContentDto> savedContent;
}
