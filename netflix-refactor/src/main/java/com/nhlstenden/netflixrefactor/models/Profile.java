package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer age;
    private String language;
    private byte[] profilePhoto;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> preferredGenres = new HashSet<>();

    private boolean interestedInSeries;
    private boolean interestedInFilms;
    private Integer minimumAge;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<ViewingClassification> viewingClassifications = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WatchlistItem> watchlist = new HashSet<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ViewingHistory> viewingHistory = new HashSet<>();
}
