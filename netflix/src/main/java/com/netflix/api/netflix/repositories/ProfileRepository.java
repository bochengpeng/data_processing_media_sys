package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>
{
    List<Profile> findByName(String name);

    @Query("SELECT p FROM Profile p WHERE p.user.userId = :userId")
    List<Profile> findByUserId(@Param("userId") int userId);
}
