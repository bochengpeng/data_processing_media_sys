package org.example.moviesystem.response;

import org.example.moviesystem.model.PopularSeries;

public class PopularResponse
{
    private PopularSeries[] popularResults;

    public PopularSeries[] getPopularResults()
    {
        return popularResults;
    }

    public void setResults(PopularSeries[] results)
    {
        this.popularResults = results;
    }
}
