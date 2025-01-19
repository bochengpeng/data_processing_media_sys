package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.ViewingHistoryDto;
import com.netflix.api.netflix.dto.ViewingHistoryInternalDto;
import com.netflix.api.netflix.exception.EpisodeNotFoundException;
import com.netflix.api.netflix.exception.ProfileNotFoundException;
import com.netflix.api.netflix.exception.ViewingHistoryNotFoundException;

import java.util.List;

public interface ViewingHistoryService {
    ViewingHistoryDto createViewingHistory(int profileId, ViewingHistoryDto viewingHistoryDto) throws ProfileNotFoundException, EpisodeNotFoundException;
    List<ViewingHistoryDto> getViewingHistoriesByProfileId(int profileId) throws ProfileNotFoundException, ViewingHistoryNotFoundException;
    ViewingHistoryDto getViewingHistoryById(int historyId) throws ViewingHistoryNotFoundException;
    void deleteViewingHistory(int historyId) throws ViewingHistoryNotFoundException;
    ViewingHistoryDto updateViewingHistory(int profileId, int vhId, ViewingHistoryInternalDto viewingHistory) throws ProfileNotFoundException;
}
