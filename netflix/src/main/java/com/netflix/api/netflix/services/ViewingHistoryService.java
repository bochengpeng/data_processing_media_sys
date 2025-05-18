package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.ViewingHistoryDto;
import com.netflix.api.netflix.exception.ViewingHistoryNotFoundException;

import java.util.List;

public interface ViewingHistoryService
{
    ViewingHistoryDto createViewingHistory(ViewingHistoryDto viewingHistoryDto);

    ViewingHistoryDto updateViewingHistory(int id, ViewingHistoryDto viewingHistoryDto);

    void deleteViewingHistory(int id);

    ViewingHistoryDto getViewingHistoryById(int id);

    List<ViewingHistoryDto> getAllViewingHistories();

    List<ViewingHistoryDto> getViewingHistoriesByProfileId(int id) throws ViewingHistoryNotFoundException;
}
