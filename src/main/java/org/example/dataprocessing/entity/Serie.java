package org.example.dataprocessing.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Serie extends Content
{
    private int totalSeasons;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Episode> episodes;

    // Constructors
    public Serie()
    {
        super(); // No-arg constructor from Content
    }

    public Serie(Long contentId, String title, String description, String genre, AgeRating ageRating, List<String> supportedQualities, int totalSeasons, List<Episode> episodes)
    {
        super(contentId, title, description, genre, ageRating, supportedQualities);
        this.totalSeasons = totalSeasons;
        this.episodes = episodes;
    }

    // Getters and Setters
    public int getTotalSeasons()
    {
        return totalSeasons;
    }

    public void setTotalSeasons(int totalSeasons)
    {
        this.totalSeasons = totalSeasons;
    }

    public List<Episode> getEpisodes()
    {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes)
    {
        this.episodes = episodes;
    }

}
