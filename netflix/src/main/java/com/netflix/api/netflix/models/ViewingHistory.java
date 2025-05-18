package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viewing_history")
@Setter
@Getter
public class ViewingHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @OneToMany(mappedBy = "viewingHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViewingSession> watchedList;

    private LocalDateTime viewedAt;

    private LocalDateTime stopAt;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = false)
    private Episode episode;

    private Integer watchDuration; // in seconds
    private Integer resumePosition; // in seconds
    private boolean completed;
    private boolean subtitlesEnabled;

    private VideoQuality selectedQuality;
}
