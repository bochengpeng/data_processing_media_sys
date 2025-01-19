package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Language;
import com.netflix.api.netflix.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>
{
    List<Profile> findByName(String name);
//    List<Profile> findByAge(int age);
//    List<Profile> findByLanguage(Language language);

//    List<Profile> findByUserId(int userId);
}
