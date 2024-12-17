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
    private String profilePhotoUrl;

    @Enumerated(EnumType.STRING)
    private Language language;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<ContentPreference> preferences;

    @OneToOne(cascade = CascadeType.ALL)
    private WatchList watchList;

    @OneToOne(cascade = CascadeType.ALL)
    private ViewingHistory viewingHistory;
}
