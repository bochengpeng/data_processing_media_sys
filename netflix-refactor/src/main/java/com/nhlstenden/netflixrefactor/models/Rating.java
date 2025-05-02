package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private Long contentId;

    @Column(nullable = false)
    private Integer score; // 1-5 stars

    private String comment;

    private LocalDateTime createdAt;
}
