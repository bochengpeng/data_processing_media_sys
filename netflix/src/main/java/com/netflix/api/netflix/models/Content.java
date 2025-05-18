package com.netflix.api.netflix.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "contents")
@Setter
@Getter
public abstract class Content
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;
    @Column(length = 5000)
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private Integer duration;

    private Genre genre;
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<ContentClassification> contentClassification = new HashSet<>();

    private AgeRating ageRating;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<VideoQuality> availableQualities = new HashSet<>();

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Subtitle> subtitles = new ArrayList<>();
}
