package com.nhlstenden.netflixrefactor.services.serviceimpl;

import com.nhlstenden.netflixrefactor.dtos.ContentDto;
import com.nhlstenden.netflixrefactor.models.Content;
import com.nhlstenden.netflixrefactor.models.Movie;
import com.nhlstenden.netflixrefactor.repositories.ContentRepository;
import com.nhlstenden.netflixrefactor.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService
{

    private final ContentRepository contentRepository;

    @Override
    public Content createContent(ContentDto contentDto) {
        Content content = new Movie(); // Assuming default subclass (e.g., Movie or Series)
        content.setTitle(contentDto.getTitle());
        content.setDescription(contentDto.getDescription());
        content.setReleaseYear(contentDto.getReleaseYear());
        content.setDuration(contentDto.getDuration());
        content.setGenres(contentDto.getGenres());
        content.setViewingClassifications(contentDto.getViewingClassifications());
        content.setMinimumAge(contentDto.getMinimumAge());
        content.setAvailableQualities(contentDto.getAvailableQualities());
        return contentRepository.save(content);
    }

    @Override
    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    @Override
    public Content getContentById(Long id) {
        return contentRepository.findById(id).orElseThrow(() -> new RuntimeException("Content not found"));
    }
}

