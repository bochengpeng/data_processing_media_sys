package com.netflix.api.netflix.models;

import lombok.Getter;

@Getter
public enum Genre {
    ACTION(1),
    COMEDY(2),
    DRAMA(3),
    HORROR(4);

    private final int value;

    Genre(int value) {
        this.value = value;
    }

    public static Genre fromValue(int value) {
        for (Genre genre : Genre.values()) {
            if (genre.value == value) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Unknown genre value: " + value);
    }
}

