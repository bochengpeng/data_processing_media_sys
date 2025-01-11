package org.example.moviesystem.response;

import org.example.moviesystem.model.PopularSeries;
import org.example.moviesystem.model.RatedSeries;

public class RatedResponse
{
    private RatedSeries[] results;
    private PopularSeries[] popularResults;

    public RatedSeries[] getResults()
    {
        return results;
    }

    public void setResults(RatedSeries[] results)
    {
        this.results = results;
    }

    public PopularSeries[] getPopularResults()
    {
        return this.popularResults;
    }

    public void setPopularResults(PopularSeries[] popularResults)
    {
        this.popularResults = popularResults;
    }
}
