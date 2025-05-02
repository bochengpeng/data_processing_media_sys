package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "viewing_history")
//@Setter
//@Getter
//public class ViewingHistory
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int historyId;
//
//    @OneToOne
//    @JoinColumn(name = "profile_id", nullable = false)
//    private Profile profile; // Association with Profile
//
//    @OneToMany(mappedBy = "viewingHistory", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ViewingSession> watchedList;
//
//    private LocalDateTime viewedAt;
//
//    private LocalDateTime stopAt;
//    @ManyToOne
//    @JoinColumn(name = "movie_id", nullable = false)
//    private Movie movie;
//
//    @ManyToOne
//    @JoinColumn(name = "episode_id", nullable = false)
//    private Episode episode;
//
//    private double watchedPercentage;
//}
@Entity
@Data
public class ViewingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private Long contentId;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private LocalDateTime viewedAt;
    private Integer watchDuration; // in seconds
    private Integer resumePosition; // in seconds
    private boolean completed;

    // For series
    private Long episodeId;

    // Additional data for analytics
    private boolean subtitlesEnabled;
    private String subtitleLanguage;
    private VideoQuality selectedQuality;
}