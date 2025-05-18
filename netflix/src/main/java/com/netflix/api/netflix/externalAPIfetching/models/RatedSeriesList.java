package com.netflix.api.netflix.externalAPIfetching.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Setter;

import java.util.List;

@Setter
@XmlRootElement(name = "seriesList")
public class RatedSeriesList
{
    private List<RatedSeries> series;

    @XmlElement(name = "series")
    public List<RatedSeries> getSeries()
    {
        return this.series;
    }
}
