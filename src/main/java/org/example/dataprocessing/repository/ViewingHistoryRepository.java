package org.example.dataprocessing.repository;

import org.example.dataprocessing.entity.ViewingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewingHistoryRepository extends JpaRepository<ViewingHistory, Long>
{
    List<ViewingHistory> findByProfileId(Long profileId);
}
