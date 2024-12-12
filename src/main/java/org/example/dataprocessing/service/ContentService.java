package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.Content;
import org.example.dataprocessing.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService
{

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository)
    {
        this.contentRepository = contentRepository;
    }

    public List<Content> getAllContent()
    {
        return contentRepository.findAll();
    }

    public Content getContentById(Long id)
    {
        return contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Content not found"));
    }

    public List<Content> getContentByGenre(Long contentId)
    {
        return contentRepository.findByContentId(contentId);
    }

    public Content addContent(Content content)
    {
        return contentRepository.save(content);
    }
}
