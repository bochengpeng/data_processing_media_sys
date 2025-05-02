package com.nhlstenden.netflixrefactor.externalApiFetching.response;

import com.nhlstenden.netflixrefactor.externalApiFetching.entities.PopularSeries;
import com.nhlstenden.netflixrefactor.externalApiFetching.entities.RatedSeries;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RatedResponse
{
    private RatedSeries[] results;
    private PopularSeries[] popularResults;
}
