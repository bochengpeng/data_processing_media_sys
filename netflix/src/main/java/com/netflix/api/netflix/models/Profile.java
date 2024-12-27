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
@Table(name = "profiles")
public class Profile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;
    private String name;
    private int age;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    private String profilePhotoUrl = "placeholder.jpeg";
    @Column(name = "profile_photo_url", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'placeholder.jpeg'")
    private String profilePhotoUrl = "placeholder.jpeg";


    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToOne(cascade = CascadeType.ALL)
    private WatchList watchList;

    @OneToOne(cascade = CascadeType.ALL)
    private ViewingHistory viewingHistory;
}
