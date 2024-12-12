package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contents")
public class Content
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentId;
    private String title;
    private String description;
    private Genre genre;
    private AgeRating ageRating;

    @ElementCollection
    private List<String> supportedQualities;
}
