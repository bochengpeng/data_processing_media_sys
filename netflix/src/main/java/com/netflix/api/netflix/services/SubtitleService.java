package com.netflix.api.netflix.services;

import com.netflix.api.netflix.dto.SubtitleDto;
import com.netflix.api.netflix.exception.ContentNotFoundException;
import com.netflix.api.netflix.exception.SubtitleNotFoundException;

import java.util.List;

public interface SubtitleService
{
    SubtitleDto createSubtitle(SubtitleDto subtitleDto) throws ContentNotFoundException;

    List<SubtitleDto> getSubtitlesByContentId(int contentId);

    SubtitleDto updateSubtitle(int id, SubtitleDto subtitleDto) throws SubtitleNotFoundException;

    void deleteSubtitle(int id) throws SubtitleNotFoundException;
}

