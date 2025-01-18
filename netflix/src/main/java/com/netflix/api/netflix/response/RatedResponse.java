package com.netflix.api.netflix.response;

import com.netflix.api.netflix.models.PopularSeries;
import com.netflix.api.netflix.models.RatedSeries;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RatedResponse
{
    private RatedSeries[] results;
    private PopularSeries[] popularResults;
}
