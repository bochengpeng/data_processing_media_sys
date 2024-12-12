package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.ViewingHistoryDto;
import com.netflix.api.netflix.exception.ViewingHistoryNotFoundException;
import com.netflix.api.netflix.models.ViewingHistory;
import com.netflix.api.netflix.repository.ViewingHistoryRepository;
import com.netflix.api.netflix.services.ViewingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewingHistoryServiceImpl implements ViewingHistoryService
{
    private final ViewingHistoryRepository viewingHistoryRepository;

    @Autowired
    public ViewingHistoryServiceImpl(ViewingHistoryRepository viewingHistoryRepository) {
        this.viewingHistoryRepository = viewingHistoryRepository;
    }

    private ViewingHistoryDto mapToDto(ViewingHistory viewingHistory) {
        return new ViewingHistoryDto(viewingHistory.getHistoryId(), viewingHistory.getWatchedList(), viewingHistory.getStopAt());
    }

    private ViewingHistory mapToEntity(ViewingHistoryDto viewingHistoryDto) {
        return new ViewingHistory(viewingHistoryDto.getHistoryId(), viewingHistoryDto.getWatchedPercentage(), viewingHistoryDto.getStopAt());
    }

    @Override
    public ViewingHistoryDto createViewingHistory(int userId, ViewingHistoryDto viewingHistoryDto)
    {
        ViewingHistory viewingHistory = mapToEntity(viewingHistoryDto);
        ViewingHistory newViewingHistory = viewingHistoryRepository.save(viewingHistory);
        return mapToDto(newViewingHistory);
    }

    @Override
    public List<ViewingHistoryDto> getViewingHistoriesByUserId(int userId)
    {
        return null;
    }

    @Override
    public ViewingHistoryDto getViewingHistoryById(int userId, int historyId) throws ViewingHistoryNotFoundException
    {
        ViewingHistory viewingHistory = viewingHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ViewingHistoryNotFoundException("Viewing history not found"));
        return mapToDto(viewingHistory);
    }

    @Override
    public void deleteViewingHistory(int userId, int historyId) throws ViewingHistoryNotFoundException
    {
        ViewingHistory viewingHistory = viewingHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ViewingHistoryNotFoundException("Viewing history not found for delete"));
        viewingHistoryRepository.delete(viewingHistory);
    }
}

