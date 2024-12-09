package org.example.dataprocessing.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Movie extends Content
{
    private int duration; // Duration of the movie in minutes

    @ElementCollection
    @CollectionTable(name = "movie_cast", joinColumns = @JoinColumn(name = "movie_id"))
    private List<String> cast; // List of cast members

    private LocalDate releaseDate; // Release date of the movie

    // Constructors
    public Movie()
    {
        super(); // No-arg constructor from Content
    }

    public Movie(Long contentId, String title, String description, String genre, AgeRating ageRating, List<String> supportedQualities, int duration, List<String> cast, LocalDate releaseDate)
    {
        super(contentId, title, description, genre, ageRating, supportedQualities);
        this.duration = duration;
        this.cast = cast;
        this.releaseDate = releaseDate;
    }

    public int getDuration()
    {
        return this.duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public List<String> getCast()
    {
        return this.cast;
    }

    public void setCast(List<String> cast)
    {
        this.cast = cast;
    }

    public LocalDate getReleaseDate()
    {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate)
    {
        this.releaseDate = releaseDate;
    }
}
