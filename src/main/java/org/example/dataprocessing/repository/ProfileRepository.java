package org.example.dataprocessing.repository;

import org.example.dataprocessing.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>
{
    List<Profile> findByProfileId(Long profileId);
}
