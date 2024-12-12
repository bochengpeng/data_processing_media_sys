package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viewing_history")
public class ViewingHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyId;

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile; // Association with Profile

    @OneToMany(mappedBy = "viewingHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViewingSession> watchedList; // Correct OneToMany relationship

    private LocalDateTime viewedAt;

    private LocalDateTime stopAt;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
