package org.example.dataprocessing.repository;

import org.example.dataprocessing.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long>
{
    List<Content> findByContentId(Long contentId);
}
