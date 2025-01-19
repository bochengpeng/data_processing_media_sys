package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viewing_session")
@Getter
@Setter
public class ViewingSession
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private ViewingHistory viewingHistory;

    @Column
    private double watchedPercentage;

    @Column
    private LocalDateTime stopAt;
}
