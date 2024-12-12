package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private int duration; // Duration of the movie in minutes
    private String title;
    private Genre genre;
    private ContentClassification contentClassification;
    private AgeRating ageRating;

//    @ElementCollection
//    @CollectionTable(name = "movie_cast", joinColumns = @JoinColumn(name = "movie_id"))
//    private List<String> cast; // List of cast members

    private LocalDate releaseDate;
}
