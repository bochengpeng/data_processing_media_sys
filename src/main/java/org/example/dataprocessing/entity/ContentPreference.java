package org.example.dataprocessing.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class ContentPreference
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @ElementCollection
    private List<Genre> preferredGenres;

    @Enumerated(EnumType.STRING)
    private ContentClassification contentClassification;

    @ElementCollection
    private List<String> preferredQualities;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private AgeRating minAgeRating;

    // Constructors
    public ContentPreference()
    {
    }

    public ContentPreference(List<Genre> preferredGenres, List<String> preferredQualities, User user, AgeRating minAgeRating)
    {
        this.preferredGenres = preferredGenres;
        this.preferredQualities = preferredQualities;
        this.user = user;
        this.minAgeRating = minAgeRating;
    }

    // Getters and Setters
    public Long getPreferenceId()
    {
        return preferenceId;
    }

    public void setPreferenceId(Long preferenceId)
    {
        this.preferenceId = preferenceId;
    }

    public ContentClassification getContentClassification()
    {
        return this.contentClassification;
    }

    public void setContentClassification(ContentClassification contentClassification)
    {
        this.contentClassification = contentClassification;
    }

    public AgeRating getMinAgeRating()
    {
        return this.minAgeRating;
    }

    public void setMinAgeRating(AgeRating minAgeRating)
    {
        this.minAgeRating = minAgeRating;
    }

    public List<Genre> getPreferredGenres()
    {
        return preferredGenres;
    }

    public void setPreferredGenres(List<Genre> preferredGenres)
    {
        this.preferredGenres = preferredGenres;
    }

    public List<String> getPreferredQualities()
    {
        return preferredQualities;
    }

    public void setPreferredQualities(List<String> preferredQualities)
    {
        this.preferredQualities = preferredQualities;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    // Methods
    public void addPreferredGenre(Genre genre)
    {
        this.preferredGenres.add(genre);
    }

    public void addPreferredQuality(String quality)
    {
        this.preferredQualities.add(quality);
    }

    public void removePreferredGenre(String genre)
    {
        this.preferredGenres.remove(genre);
    }

    public void removePreferredQuality(String quality)
    {
        this.preferredQualities.remove(quality);
    }

}
