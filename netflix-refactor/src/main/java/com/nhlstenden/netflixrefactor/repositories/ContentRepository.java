package com.nhlstenden.netflixrefactor.repositories;

import com.nhlstenden.netflixrefactor.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long>
{
    List<Content> findByGenresContaining(String genre);
}

