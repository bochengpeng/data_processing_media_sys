package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Subtitle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String filePath;
}
