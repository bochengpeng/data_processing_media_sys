package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "contents")
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;
    private Integer releaseYear;
    private Integer duration;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> genres = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<ViewingClassification> viewingClassifications = new HashSet<>();

    private Integer minimumAge;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<VideoQuality> availableQualities = new HashSet<>();
}

