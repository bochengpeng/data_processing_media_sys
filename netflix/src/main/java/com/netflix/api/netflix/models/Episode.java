package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "episodes")
public class Episode
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int episodeId; // Unique ID for the episode
    private int episodeNumber;
    private int seasonNumber;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    private Series series;
}
