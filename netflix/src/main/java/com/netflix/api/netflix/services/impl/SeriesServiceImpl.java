package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.SeriesDto;
import com.netflix.api.netflix.models.Series;
import com.netflix.api.netflix.repositories.SeriesRepository;
import com.netflix.api.netflix.services.SeriesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public SeriesDto createSeries(SeriesDto seriesDto)
    {
        try
        {
            Series series = mapToEntity(seriesDto);
            Series savedSeries = this.seriesRepository.save(series);

            return mapToDto(savedSeries);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create series: " + e.getMessage(), e);
        }
    }

    @Override
    public List<SeriesDto> getAllSeries()
    {
        try
        {
            List<Series> seriesList = this.seriesRepository.findAll();

            return seriesList.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to retrieve series list: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public SeriesDto updateSeries(int seriesId, SeriesDto seriesDto)
    {
        try
        {
            Series existingSeries = this.seriesRepository.findById(seriesId)
                    .orElseThrow(() -> new RuntimeException("Series not found"));

            existingSeries.setTotalSeasons(seriesDto.getTotalSeasons());
            existingSeries.setEpisodes(seriesDto.getEpisodes());
            existingSeries.setCurrentSeason(seriesDto.getCurrentSeason());

            Series updatedSeries = this.seriesRepository.save(existingSeries);

            return mapToDto(updatedSeries);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to update series: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void deleteSeries(int seriesId)
    {
        try
        {
            Series series = this.seriesRepository.findById(seriesId)
                    .orElseThrow(() -> new RuntimeException("Series not found"));

            this.seriesRepository.delete(series);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to delete series: " + e.getMessage(), e);
        }
    }

    @Override
    public SeriesDto getSeriesById(int seriesId)
    {
        try
        {
            Series series = this.seriesRepository.findById(seriesId)
                    .orElseThrow(() -> new RuntimeException("Series not found"));

            return mapToDto(series);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to get series: " + e.getMessage(), e);
        }
    }

    private SeriesDto mapToDto(Series series)
    {
        SeriesDto seriesDto = new SeriesDto();
        seriesDto.setSeriesId(series.getId());
        seriesDto.setTitle(series.getTitle());
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