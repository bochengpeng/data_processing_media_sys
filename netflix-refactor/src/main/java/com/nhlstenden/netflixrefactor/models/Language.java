package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code; // e.g., en-US

    @Column(nullable = false)
    private String name; // e.g., English (US)
}
