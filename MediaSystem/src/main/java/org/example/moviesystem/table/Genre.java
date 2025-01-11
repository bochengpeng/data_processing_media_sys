package org.example.moviesystem.table;

import jakarta.persistence.*;

@Entity
public class Genre {

    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @Column(name = "genre_name", nullable = false,  unique = true)
    private String genreName;

    // Getters and setters
    public Long getGenreId()
    {
        return genreId;
    }

    public void setGenreId(Long genreId)
    {
        this.genreId = genreId;
    }

    public String getGenreName()
    {
        return genreName;
    }

    public void setGenreName(String genreName)
    {
        this.genreName = genreName;
    }
}