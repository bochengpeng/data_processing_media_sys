package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.ViewingSession;
import org.example.dataprocessing.repository.ViewingSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViewingSessionService
{
    private final ViewingSessionRepository viewingSessionRepository;

    public ViewingSessionService(ViewingSessionRepository viewingSessionRepository)
    {
        this.viewingSessionRepository = viewingSessionRepository;
    }

    /**
     * Create a new viewing session.
     */
    public ViewingSession createViewingSession(ViewingSession viewingSession)
    {
        return viewingSessionRepository.save(viewingSession);
    }

    /**
     * Fetch a viewing session by its ID.
     */
    public Optional<ViewingSession> getViewingSessionById(Long sessionId)
    {
        return viewingSessionRepository.findById(sessionId);
    }

    /**
     * Fetch all viewing sessions.
     */
    public List<ViewingSession> getAllViewingSessions()
    {
        return viewingSessionRepository.findAll();
    }

    /**
     * Update a viewing session.
     */
    public ViewingSession updateViewingSession(Long sessionId, ViewingSession updatedSession)
    {
        return viewingSessionRepository.findById(sessionId).map(existingSession -> {
            existingSession.setContent(updatedSession.getContent());
            existingSession.setStartTime(updatedSession.getStartTime());
            existingSession.setEndTime(updatedSession.getEndTime());
            existingSession.setViewingHistory(updatedSession.getViewingHistory());
            return viewingSessionRepository.save(existingSession);
        }).orElseThrow(() -> new IllegalArgumentException("Viewing session not found with ID: " + sessionId));
    }

    /**
     * Delete a viewing session by ID.
     */
    public void deleteViewingSession(Long sessionId)
    {
        if (!viewingSessionRepository.existsById(sessionId))
        {
            throw new IllegalArgumentException("Viewing session not found with ID: " + sessionId);
        }
        viewingSessionRepository.deleteById(sessionId);
    }

    /**
     * Fetch viewing sessions by viewing history ID.
     */
    public List<ViewingSession> getViewingSessionsByHistoryId(Long id)
    {
        return viewingSessionRepository.findAll()
                .stream()
                .filter(session -> session.getViewingHistory().getHistoryId().equals(id))
                .toList();
    }
}
