package org.example.dataprocessing.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public abstract class Content
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;
    private String title;
    private String description;

    @ElementCollection(fetch = FetchType.EAGER) // Maps a collection of basic types or embeddables
    @Enumerated(EnumType.STRING) // Store the enum values as strings in the database
    private List<Genre> genres;

    private AgeRating ageRating;

    @ElementCollection
    private List<String> supportedQualities;

    public Content()
    {
    }

    public Content(Long contentId, String title, String description, List<Genre> genres, AgeRating ageRating, List<String> supportedQualities)
    {

        this.contentId = contentId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.ageRating = ageRating;
        this.supportedQualities = supportedQualities;

    }

    public Long getContentId()
    {
        return this.contentId;
    }

    public void setContentId(Long contentId)
    {
        this.contentId = contentId;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<Genre> getGenres()
    {
        return this.genres;
    }

    public void setGenres(List<Genre> genres)
    {
        this.genres = genres;
    }

    public AgeRating getAgeRating()
    {
        return this.ageRating;
    }

    public void setAgeRating(AgeRating ageRating)
    {
        this.ageRating = ageRating;
    }

    public List<String> getSupportedQualities()
    {
        return this.supportedQualities;
    }

    public void setSupportedQualities(List<String> supportedQualities)
    {
        this.supportedQualities = supportedQualities;
    }
}

