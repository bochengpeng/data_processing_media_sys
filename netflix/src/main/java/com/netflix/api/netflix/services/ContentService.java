package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.ContentDto;

import java.util.List;

public interface ContentService {
    ContentDto createContent(ContentDto contentDto);
    ContentDto getContentById(int contentId);
    List<ContentDto> getAllContents();
    ContentDto updateContent(int contentId, ContentDto contentDto);
    void deleteContent(int contentId);
}

