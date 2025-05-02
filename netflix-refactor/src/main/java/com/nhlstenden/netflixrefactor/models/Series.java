package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Series extends Content {
    private Integer seasonCount;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private Set<Episode> episodes = new HashSet<>();
}
