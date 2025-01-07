package org.example.moviesystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PopularSeries
{
    private String name;
    @JsonProperty("poster_path")
    private String posterPath;
    private String overview;
    @JsonProperty("origin_country")
    private List<String> originCountry; // Changed from String to List<String>
    private double popularity;

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPosterPath()
    {
        return this.posterPath;
    }

    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }

    public String getOverview()
    {
        return this.overview;
    }

    public void setOverview(String overview)
    {
        this.overview = overview;
    }

    public List<String> getOriginCountry()
    { // Updated getter
        return this.originCountry;
    }

    public void setOriginCountry(List<String> originCountry)
    { // Updated setter
        this.originCountry = originCountry;
    }

    public double getPopularity()
    {
        return this.popularity;
    }

    public void setPopularity(double popularity)
    {
        this.popularity = popularity;
    }
}
