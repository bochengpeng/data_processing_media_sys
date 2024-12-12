package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.Serie;
import org.example.dataprocessing.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService
{

    private final SerieRepository serieRepository;

    // Constructor Injection
    public SerieService(SerieRepository serieRepository)
    {
        this.serieRepository = serieRepository;
    }

    // Create a new Serie
    public Serie createSerie(Serie serie)
    {
        return serieRepository.save(serie);
    }

    // Get a Serie by its ID
    public Optional<Serie> getSerieById(Long serieId)
    {
        return serieRepository.findById(serieId);
    }

    // Fetch all series
    public List<Serie> getAllSeries()
    {
        return serieRepository.findAll();
    }

    // Update a Serie
    public Serie updateSerie(Long serieId, Serie updatedSerie)
    {
        return serieRepository.findById(serieId).map(existingSerie ->
        {
            existingSerie.setTitle(updatedSerie.getTitle());
            existingSerie.setDescription(updatedSerie.getDescription());
            existingSerie.setGenres(updatedSerie.getGenres());
            existingSerie.setAgeRating(updatedSerie.getAgeRating());
            existingSerie.setSupportedQualities(updatedSerie.getSupportedQualities());
            existingSerie.setTotalSeasons(updatedSerie.getTotalSeasons());
            return serieRepository.save(existingSerie);
        }).orElseThrow(() -> new IllegalArgumentException("Serie not found with ID: " + serieId));
    }

    // Delete a Serie by its ID
    public void deleteSerie(Long serieId)
    {
        if (!serieRepository.existsById(serieId))
        {
            throw new IllegalArgumentException("Serie not found with ID: " + serieId);
        }
        serieRepository.deleteById(serieId);
    }

    // Fetch series by the number of total seasons
    public List<Serie> getSeriesByTotalSeasons(int totalSeasons)
    {
        return serieRepository.findAll()
                .stream()
                .filter(serie -> serie.getTotalSeasons() == totalSeasons)
                .toList();
    }
}
