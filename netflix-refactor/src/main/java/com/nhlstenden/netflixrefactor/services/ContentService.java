package com.nhlstenden.netflixrefactor.services;

import com.nhlstenden.netflixrefactor.dtos.ContentDto;
import com.nhlstenden.netflixrefactor.models.Content;

import java.util.List;

public interface ContentService {
    Content createContent(ContentDto contentDto);
    List<Content> getAllContent();
    Content getContentById(Long id);
}

