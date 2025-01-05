package com.netflix.api.netflix.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReleaseDateResultDTO {
    private String iso_3166_1; // Country code (e.g., "US")
    private List<ReleaseDateDto> release_dates; // List of release dates for this region
}

