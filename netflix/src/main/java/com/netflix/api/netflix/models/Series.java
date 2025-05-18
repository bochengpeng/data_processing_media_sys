package com.netflix.api.netflix.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "series")
@Getter
@Setter
public class Series extends Content
{
    private int totalSeasons;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Episode> episodes;
    private String currentSeason;
}
