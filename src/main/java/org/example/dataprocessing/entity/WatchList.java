package org.example.dataprocessing.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class WatchList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchListId;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;  // Relationship with Profile

    @ManyToMany
    @JoinTable(
            name = "watchlist_saved_content",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private List<Content> savedContent; // List of Content saved by the user

    // Constructors
    public WatchList()
    {
    }

    public WatchList(Profile profile)
    {
        this.profile = profile;
        this.savedContent = new ArrayList<>();
    }

    public Long getWatchListId()
    {
        return this.watchListId;
    }

    public void setWatchListId(Long watchListId)
    {
        this.watchListId = watchListId;
    }

    public Profile getProfile()
    {
        return this.profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    public List<Content> getSavedContent()
    {
        return this.savedContent;
    }

    public void setSavedContent(List<Content> savedContent)
    {
        this.savedContent = savedContent;
    }
}
