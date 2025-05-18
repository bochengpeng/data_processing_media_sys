package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.Subtitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtitleRepository extends JpaRepository<Subtitle, Integer>
{
    List<Subtitle> findByContentId(int contentId);
}

