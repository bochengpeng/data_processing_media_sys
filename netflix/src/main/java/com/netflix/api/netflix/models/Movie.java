package com.netflix.api.netflix.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Setter
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private int duration;
    private String title;
    private AgeRating ageRating;
    private ContentClassification contentClassification;
    private Genre genre;

    @Column(length = 5000) // Allow longer descriptions
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")// for front-end to show date correctly
    private LocalDate releaseDate;
}
