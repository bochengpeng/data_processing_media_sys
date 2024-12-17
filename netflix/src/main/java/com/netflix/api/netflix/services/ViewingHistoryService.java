package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.ViewingHistoryDto;
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
    ViewingHistoryDto createViewingHistory(int userId, ViewingHistoryDto viewingHistoryDto);
    List<ViewingHistoryDto> getViewingHistoriesByUserId(int userId);
    ViewingHistoryDto getViewingHistoryById(int userId, int historyId) throws ViewingHistoryNotFoundException;
    void deleteViewingHistory(int userId, int historyId) throws ViewingHistoryNotFoundException;
}
