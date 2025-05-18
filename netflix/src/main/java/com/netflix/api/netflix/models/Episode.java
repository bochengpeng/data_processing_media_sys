package com.netflix.api.netflix.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "episodes")
public class Episode
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int episodeId;
    private int episodeNumber;
    private int seasonNumber;
    private int duration;
    private String title;
    private String description;
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id", nullable = false)
    @JsonBackReference
    private Series series;
}
