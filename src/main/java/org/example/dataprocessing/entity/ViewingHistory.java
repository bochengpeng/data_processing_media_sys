package org.example.dataprocessing.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class ViewingHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile; // Association with Profile

    @OneToMany(mappedBy = "viewingHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViewingSession> watchedList; // Correct OneToMany relationship

    private LocalDateTime viewedAt;

    private long durationWatched;

    // Constructors
    public ViewingHistory()
    {
    }

    public ViewingHistory(Profile profile)
    {
        this.profile = profile;
        this.watchedList = new ArrayList<>();
        this.viewedAt = null;
        this.durationWatched = 0;
    }

    public Long getHistoryId()
    {
        return this.historyId;
    }

    public void setHistoryId(Long historyId)
    {
        this.historyId = historyId;
    }

    public Profile getProfile()
    {
        return this.profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    public List<ViewingSession> getWatchedList()
    {
        return this.watchedList;
    }

    public void setWatchedList(List<ViewingSession> watchedList)
    {
        this.watchedList = watchedList;
    }

    public LocalDateTime getViewedAt()
    {
        return this.viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt)
    {
        this.viewedAt = viewedAt;
    }

    public long getDurationWatched()
    {
        return this.durationWatched;
    }

    public void setDurationWatched(long durationWatched)
    {
        this.durationWatched = durationWatched;
    }

}
