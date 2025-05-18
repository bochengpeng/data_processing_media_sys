package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Setter
@Getter
public class Movie extends Content
{
    private String director;

    @ElementCollection
    private Set<String> actors = new HashSet<>();
}
