package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "series")
@Getter
@Setter
public class Series
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seriesId;
    private int totalSeasons;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Episode> episodes;
    private String currentSeason;
    private String title;
}
