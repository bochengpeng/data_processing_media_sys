package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.ViewingHistory;
import org.example.dataprocessing.repository.ViewingHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewingHistoryService
{

    private final ViewingHistoryRepository viewingHistoryRepository;

    public ViewingHistoryService(ViewingHistoryRepository viewingHistoryRepository)
    {
        this.viewingHistoryRepository = viewingHistoryRepository;
    }

    public List<ViewingHistory> getViewingHistoryByProfileId(Long profileId)
    {
        return viewingHistoryRepository.findByProfileId(profileId);
    }

    public ViewingHistory addViewingHistory(ViewingHistory viewingHistory)
    {
        return viewingHistoryRepository.save(viewingHistory);
    }
}
