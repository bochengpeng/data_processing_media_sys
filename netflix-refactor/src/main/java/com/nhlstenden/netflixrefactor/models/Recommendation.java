package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private Long contentId;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private Double relevanceScore;

    @Enumerated(EnumType.STRING)
    private RecommendationType type;

    private LocalDateTime createdAt;
}

