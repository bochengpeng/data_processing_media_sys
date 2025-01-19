package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.ViewingHistoryDto;
import com.netflix.api.netflix.dto.ViewingHistoryInternalDto;
import com.netflix.api.netflix.exception.ProfileNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.exception.ViewingHistoryNotFoundException;

import java.util.List;

//@Service
//public interface ViewingHistoryService {
//    @Autowired
//    private ViewingHistoryRepository viewingHistoryRepository;
//
//    public List<ViewingHistory> getRecentViewings(int profileId) {
//        return viewingHistoryRepository.findByProfile_ProfileId(profileId);
//    }
//
//    public int getTotalViewingSessions(int profileId) {
//        return viewingHistoryRepository.countByProfile_ProfileId(profileId);
//    }
//
//    public abstract ViewingHistoryDto createViewingHistory(ViewingHistoryDto viewingHistoryDto);
//
//    public abstract List<ViewingHistoryDto> getAllViewingHistories();
public interface ViewingHistoryService {
    ViewingHistoryDto createViewingHistory(int profileId, ViewingHistoryDto viewingHistoryDto) throws ProfileNotFoundException;
    List<ViewingHistoryDto> getViewingHistoriesByProfileId(int profileId) throws ProfileNotFoundException, ViewingHistoryNotFoundException;
    ViewingHistoryDto getViewingHistoryById(int historyId) throws ViewingHistoryNotFoundException;
    void deleteViewingHistory(int historyId) throws ViewingHistoryNotFoundException;

    ViewingHistoryDto updateViewingHistory(int profileId, int vhId, ViewingHistoryInternalDto viewingHistory) throws ProfileNotFoundException;
}
