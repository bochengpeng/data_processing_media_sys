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
@Table(name = "series")
public class Series
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seriesId;
    private int totalSeasons;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Episode> episodes;


}
