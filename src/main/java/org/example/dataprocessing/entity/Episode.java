package org.example.dataprocessing.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Episode extends Content
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long episodeId; // Unique ID for the episode

    private int episodeNumber;
    private int seasonNumber;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    private Serie serie;       // The series this episode belongs to

    // Constructors
    public Episode()
    {
        super(); // No-arg constructor from Content
    }

    public Episode(Long contentId, String title, String description, String genre, AgeRating ageRating, List<String> supportedQualities, Long episodeId, int episodeNumber, int seasonNumber, int duration, Serie serie)
    {
        super(contentId, title, description, genre, ageRating, supportedQualities);
        this.episodeId = episodeId;
        this.episodeNumber = episodeNumber;
        this.seasonNumber = seasonNumber;
        this.duration = duration;
        this.serie = serie;
    }

    public Long getEpisodeId()
    {
        return this.episodeId;
    }

    public void setEpisodeId(Long episodeId)
    {
        this.episodeId = episodeId;
    }

    public int getEpisodeNumber()
    {
        return this.episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber)
    {
        this.episodeNumber = episodeNumber;
    }

    public int getSeasonNumber()
    {
        return this.seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber)
    {
        this.seasonNumber = seasonNumber;
    }

    public int getDuration()
    {
        return this.duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public Serie getSerie()
    {
        return this.serie;
    }

    public void setSerie(Serie serie)
    {
        this.serie = serie;
    }
}
