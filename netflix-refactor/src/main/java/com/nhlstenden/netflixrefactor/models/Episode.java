package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Data;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "episodes")
//public class Episode
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int episodeId;
//    private int episodeNumber;
//    private int seasonNumber;
//    private int duration;
//    private String title;
//    private String description;
//    private LocalDate releaseDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "series_id", nullable = false)
//    private Series series;
//}
@Entity
@Data
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer seasonNumber;
    private Integer episodeNumber;
    private Integer duration; // in minutes

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;
}