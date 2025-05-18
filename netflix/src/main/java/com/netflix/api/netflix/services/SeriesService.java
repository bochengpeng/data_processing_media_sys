package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.SeriesDto;

import java.util.List;

public interface SeriesService
{
    SeriesDto createSeries(SeriesDto seriesDto);
    List<SeriesDto> getAllSeries();
    SeriesDto updateSeries(int seriesId, SeriesDto seriesDt);
    void deleteSeries(int seriesId);
    SeriesDto getSeriesById(int seriesId);
}
