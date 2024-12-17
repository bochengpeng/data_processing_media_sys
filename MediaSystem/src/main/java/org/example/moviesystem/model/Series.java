package org.example.moviesystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Series
{
    private String name;
    @JsonProperty("vote_average")
    private double voteAverage; // This corresponds to the "vote_average" in the API response
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
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