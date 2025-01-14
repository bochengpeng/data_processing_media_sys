package com.netflix.api.netflix.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "movies")
//public class Movie
//{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int movieId;
//    private int duration; // Duration of the movie in minutes
//    private String title;
//    private Genre genre;
//    private ContentClassification contentClassification;
//    private AgeRating ageRating;
//    private String description;
//
////    @ElementCollection
////    @CollectionTable(name = "movie_cast", joinColumns = @JoinColumn(name = "movie_id"))
////    private List<String> cast; // List of cast members
//
//    private LocalDate releaseDate;
//}

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
    private short ageRating; // Use short to match SMALLINT
    private short contentClassification;
//    @Column(name = "genre")
    private short genre;

    @Column(length = 5000) // Allow longer descriptions
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")// for front-end to show date correctly
    private LocalDate releaseDate;
}
