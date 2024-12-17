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
@Table(name = "watch_list")
public class WatchList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int watchListId;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;  // Relationship with Profile

    @ManyToMany
    @JoinTable(
            name = "watchlist_saved_content",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private List<Content> savedContent;
}
