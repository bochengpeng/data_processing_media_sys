package org.example.dataprocessing.repository;

import org.example.dataprocessing.entity.ContentPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentPreferenceRepository extends JpaRepository<ContentPreference, Long>
{
    List<ContentPreference> findByProfileId(Long profileId);
}

