package com.nhlstenden.netflixrefactor.repositories;

import com.nhlstenden.netflixrefactor.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findByUserId(Long userId);
    int countByUserId(Long userId);
}
