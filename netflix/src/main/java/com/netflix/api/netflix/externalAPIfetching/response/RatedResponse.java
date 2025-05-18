package com.netflix.api.netflix.externalAPIfetching.response;

import com.netflix.api.netflix.externalAPIfetching.models.PopularSeries;
import com.netflix.api.netflix.externalAPIfetching.models.RatedSeries;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RatedResponse
{
    private RatedSeries[] results;
    private PopularSeries[] popularResults;
}
