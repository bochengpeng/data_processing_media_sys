package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;
    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "profile_photo_url", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'placeholder.jpeg'")
    private String profilePhotoUrl = "placeholder.jpeg";

    @ElementCollection
    @CollectionTable(name = "profile_preferences", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "preference")
    private List<String> preferences = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WatchList> watchlist = new HashSet<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ViewingHistory> viewingHistory = new HashSet<>();
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<ContentClassification> contentClassifications = new HashSet<>();

    private boolean interestedInSeries;
    private boolean interestedInFilms;
}
