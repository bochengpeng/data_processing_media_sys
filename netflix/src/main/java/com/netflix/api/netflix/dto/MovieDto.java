package com.netflix.api.netflix.dto;

import lombok.Data;

@Data
public class MovieDto extends ContentDto {
    private int duration;
    private String trailerUrl;
}
