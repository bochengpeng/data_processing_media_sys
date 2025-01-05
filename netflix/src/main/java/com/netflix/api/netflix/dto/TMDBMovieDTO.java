package com.netflix.api.netflix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class TMDBMovieDTO {
//    private String title;
//    private int runtime; // Maps to `duration`
//    private String overview; // Maps to `description`
//    private String release_date; // Maps to `releaseDate`
//
//    // Additional fields can be added if needed
//}

@Data
@Getter
public class TMDBMovieDTO {
    private int id;
    private String title;
    private int runtime;
    private String overview;
    private String release_date;
    private List<GenreDto> genres; // Genre information
    private List<ReleaseDateDto> release_dates; // Certification details
}

