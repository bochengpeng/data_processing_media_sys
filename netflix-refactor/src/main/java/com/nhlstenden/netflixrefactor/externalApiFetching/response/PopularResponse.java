package com.nhlstenden.netflixrefactor.externalApiFetching.response;

import com.nhlstenden.netflixrefactor.externalApiFetching.entities.PopularSeries;

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
