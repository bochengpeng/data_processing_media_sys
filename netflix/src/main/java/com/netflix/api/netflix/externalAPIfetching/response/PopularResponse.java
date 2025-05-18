package com.netflix.api.netflix.externalAPIfetching.response;

import com.netflix.api.netflix.externalAPIfetching.models.PopularSeries;
import lombok.Getter;

@Getter
public class PopularResponse
{
    private PopularSeries[] popularResults;

    public void setResults(PopularSeries[] results)
    {
        this.popularResults = results;
    }
}
