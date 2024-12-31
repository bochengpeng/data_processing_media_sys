package com.netflix.api.netflix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMDBMovieDTO {
    private String title;
    private int runtime; // Maps to `duration`
    private String overview; // Maps to `description`
    private String release_date; // Maps to `releaseDate`

    // Additional fields can be added if needed
}

