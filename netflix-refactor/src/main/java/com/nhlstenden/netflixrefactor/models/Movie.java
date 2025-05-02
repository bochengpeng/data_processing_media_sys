package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Movie extends Content {
    private String director;

    @ElementCollection
    private Set<String> actors = new HashSet<>();
}
