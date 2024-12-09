package org.example.dataprocessing.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Profile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private String name;
    private int age;
    private String profilePhotoUrl;

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ContentPreference> preferences;

    @OneToOne(cascade = CascadeType.ALL)
    private WatchList watchList;

    @OneToOne(cascade = CascadeType.ALL)
    private ViewingHistory viewingHistory;

    public Profile()
    {
    }

    public Profile(Long profileId, String name, int age, String profilePhotoUrl, Language language, List<ContentPreference> preferences, WatchList watchList, ViewingHistory viewingHistory)
    {
        this.profileId = profileId;
        this.name = name;
        this.age = age;
        this.profilePhotoUrl = profilePhotoUrl;
        this.language = language;
        this.preferences = preferences;
        this.watchList = watchList;
        this.viewingHistory = viewingHistory;
    }

    public Long getProfileId()
    {
        return this.profileId;
    }

    public void setProfileId(Long profileId)
    {
        this.profileId = profileId;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return this.age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getProfilePhotoUrl()
    {
        return this.profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl)
    {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Language getLanguage()
    {
        return this.language;
    }

    public void setLanguage(Language language)
    {
        this.language = language;
    }

    public List<ContentPreference> getPreferences()
    {
        return this.preferences;
    }

    public void setPreferences(List<ContentPreference> preferences)
    {
        this.preferences = preferences;
    }

    public WatchList getWatchList()
    {
        return this.watchList;
    }

    public void setWatchList(WatchList watchList)
    {
        this.watchList = watchList;
    }

    public ViewingHistory getViewingHistory()
    {
        return this.viewingHistory;
    }

    public void setViewingHistory(ViewingHistory viewingHistory)
    {
        this.viewingHistory = viewingHistory;
    }
}
