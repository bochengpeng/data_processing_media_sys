package com.netflix.api.netflix.dto;

import lombok.Data;

@Data
public class ReleaseDateDto {
    private String iso_3166_1; // Country code
    private String certification;
}
