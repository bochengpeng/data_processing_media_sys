package com.nhlstenden.netflixrefactor.dtos;

import com.nhlstenden.netflixrefactor.models.ViewingClassification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {
    private Long id;
    private String name;
    private Integer age;
    private String language;
    private byte[] profilePhoto;
    private Set<String> preferredGenres;
    private boolean interestedInSeries;
    private boolean interestedInFilms;
    private Integer minimumAge;
    private Set<ViewingClassification> viewingClassifications;
    private Long userId; // to link with User
}

