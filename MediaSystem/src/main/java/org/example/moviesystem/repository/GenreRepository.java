package org.example.moviesystem.repository;

import org.example.moviesystem.table.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, String>
{
}

