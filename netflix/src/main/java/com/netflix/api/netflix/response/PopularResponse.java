package com.netflix.api.netflix.response;

import com.netflix.api.netflix.models.PopularSeries;

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
