package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.SeriesDto;
import com.netflix.api.netflix.models.Series;
import com.netflix.api.netflix.repository.SeriesRepository;
import com.netflix.api.netflix.services.SeriesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesServiceImpl implements SeriesService
{
    private final SeriesRepository seriesRepository;

    public SeriesServiceImpl(SeriesRepository seriesRepository)
    {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public SeriesDto createSeries(SeriesDto seriesDto)
    {
        Series series = mapToEntity(seriesDto);
        Series savedSeries = this.seriesRepository.save(series);

        return mapToDto(savedSeries);
    }

    @Override
    public List<SeriesDto> getAllSeries()
    {
        List<Series> seriesList = this.seriesRepository.findAll();

        return seriesList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SeriesDto updateSeries(int seriesId, SeriesDto seriesDto)
    {
        Series existingSeries = this.seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        existingSeries.setTotalSeasons(seriesDto.getTotalSeasons());
        existingSeries.setEpisodes(seriesDto.getEpisodes());
        existingSeries.setCurrentSeason(seriesDto.getCurrentSeason());

        Series updatedSeries = this.seriesRepository.save(existingSeries);

        return mapToDto(updatedSeries);
    }

    @Override
    public void deleteSeries(int seriesId)
    {
        Series series = this.seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        this.seriesRepository.delete(series);
    }

    @Override
    public SeriesDto getSeriesById(int seriesId)
    {
        Series series = this.seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        return mapToDto(series);
    }

    private SeriesDto mapToDto(Series series)
    {
        SeriesDto seriesDto = new SeriesDto();
        seriesDto.setSeriesId(series.getSeriesId());
        seriesDto.setTitle(seriesDto.getTitle());
        seriesDto.setEpisodes(series.getEpisodes());
        seriesDto.setCurrentSeason(series.getCurrentSeason());
        seriesDto.setTotalSeasons(series.getTotalSeasons());

        return seriesDto;
    }

    private Series mapToEntity(SeriesDto seriesDto)
    {
        Series series = new Series();
        series.setTitle(seriesDto.getTitle());
        series.setTotalSeasons(seriesDto.getTotalSeasons());
        series.setCurrentSeason(seriesDto.getCurrentSeason());
        series.setEpisodes(seriesDto.getEpisodes());

        return series;
    }
}
