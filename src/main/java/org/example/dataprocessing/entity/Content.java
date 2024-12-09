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
    private String genre;
    private AgeRating ageRating;

    @ElementCollection
    private List<String> supportedQualities;

    public Content()
    {
    }

    public Content(Long contentId, String title, String description, String genre, AgeRating ageRating, List<String> supportedQualities)
    {

        this.contentId = contentId;
        this.title = title;
        this.description = description;
        this.genre = genre;
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

    public String getGenre()
    {
        return this.genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
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

