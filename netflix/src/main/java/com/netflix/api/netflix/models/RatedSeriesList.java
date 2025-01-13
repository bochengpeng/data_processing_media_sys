package com.netflix.api.netflix.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "seriesList")
public class RatedSeriesList
{
    private List<RatedSeries> series;

    @XmlElement(name = "series")
    public List<RatedSeries> getSeries()
    {
        return series;
    }

    public void setSeries(List<RatedSeries> series)
    {
        this.series = series;
    }
}
