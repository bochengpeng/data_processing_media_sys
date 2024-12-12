package org.example.dataprocessing.repository;

import org.example.dataprocessing.entity.ViewingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ViewingSessionRepository extends JpaRepository<ViewingSession, Long>
{
    List<ViewingSession> findBySessionId(Long sessionId);
}
