package org.example.moviesystem.service;

import org.example.moviesystem.model.Series;

public class TMDBResponse {
    private Series[] results;

    public Series[] getResults() {
        return results;
    }

    public void setResults(Series[] results) {
        this.results = results;
    }
}