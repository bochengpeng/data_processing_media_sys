package com.netflix.api.netflix.response;

import com.netflix.api.netflix.models.PopularSeries;
import com.netflix.api.netflix.models.RatedSeries;

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
