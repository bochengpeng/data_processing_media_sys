package org.example.moviesystem.model;

import org.example.moviesystem.repository.TvSeriesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTestRunner implements CommandLineRunner {

    private final TvSeriesRepository tvSeriesRepository;

    public DatabaseTestRunner(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Testing database connection...");

        // Fetch data from the database
        tvSeriesRepository.findAll().forEach(tvSeries ->
                System.out.println(tvSeries.getTitle())
        );
    }
}
