package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class WatchList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private int contentId;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private LocalDateTime addedAt;
}
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "watch_list")
//@Getter
//@Setter
//public class WatchList
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int watchListId;
//
//    @ManyToOne
//    @JoinColumn(name = "profile_id", nullable = false)
//    private Profile profile;  // Relationship with Profile
//
//    @ManyToMany
//    @JoinTable(
//            name = "watch_list_movies",
//            joinColumns = @JoinColumn(name = "watch_list_id"),
//            inverseJoinColumns = @JoinColumn(name = "movie_id")
//    )
//    private List<Movie> movies;  // List of Movies in the watch list
//
//    @ManyToMany
//    @JoinTable(
//            name = "watch_list_episodes",
//            joinColumns = @JoinColumn(name = "watch_list_id"),
//            inverseJoinColumns = @JoinColumn(name = "episode_id")
//    )
//    private List<Episode> episodes;  // List of Episodes in the watch list
//}
