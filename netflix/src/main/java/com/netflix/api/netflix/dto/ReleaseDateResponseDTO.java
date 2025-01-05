package com.netflix.api.netflix.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReleaseDateResponseDTO {
    private int id; // Movie ID
    private List<ReleaseDateResultDTO> results; // List of results
}
