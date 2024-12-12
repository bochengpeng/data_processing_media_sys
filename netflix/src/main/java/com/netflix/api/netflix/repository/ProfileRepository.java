package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Language;
import com.netflix.api.netflix.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>
{
    // Find profiles by name
    List<Profile> findByName(String name);

    // Find profiles by age
    List<Profile> findByAge(int age);

    // Find profiles by language
    List<Profile> findByLanguage(Language language);
}
