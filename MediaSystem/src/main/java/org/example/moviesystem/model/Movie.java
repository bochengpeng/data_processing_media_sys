package org.example.moviesystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    private String title;
    @JsonProperty("vote_average")
    private double voteAverage; // This corresponds to the "vote_average" in the API response
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Ensure correct mapping if the JSON field is different
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}