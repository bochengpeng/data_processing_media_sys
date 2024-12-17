package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.ContentDto;
import com.netflix.api.netflix.models.Content;
import com.netflix.api.netflix.repository.ContentRepository;
import com.netflix.api.netflix.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService
{

    private final ContentRepository contentRepository;

    @Autowired
    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public ContentDto createContent(ContentDto contentDto) {
        // Convert DTO to entity
        Content content = mapToEntity(contentDto);

        // Save the content entity to the database
        Content savedContent = contentRepository.save(content);

        // Convert the saved entity back to DTO and return
        return mapToDto(savedContent);
    }

    @Override
    public ContentDto getContentById(int contentId) {
        // Retrieve content by ID from the database
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        // Convert entity to DTO and return
        return mapToDto(content);
    }

    @Override
    public List<ContentDto> getAllContents() {
        // Get all contents from the database
        List<Content> contentList = contentRepository.findAll();

        // Convert each content entity to DTO
        return contentList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContentDto updateContent(int contentId, ContentDto contentDto) {
        // Retrieve existing content from the database
        Content existingContent = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        // Update the content's fields with values from the DTO
        existingContent.setTitle(contentDto.getTitle());
        existingContent.setDescription(contentDto.getDescription());
        existingContent.setGenre(contentDto.getGenres());
        existingContent.setAgeRating(contentDto.getAgeRating());

        // Save the updated content entity to the database
        Content updatedContent = contentRepository.save(existingContent);

        // Convert the updated entity to DTO and return
        return mapToDto(updatedContent);
    }

    @Override
    public void deleteContent(int contentId) {
        // Retrieve content by ID
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        // Delete the content entity
        contentRepository.delete(content);
    }

    // Convert Content entity to ContentDto
    private ContentDto mapToDto(Content content) {
        ContentDto contentDto = new ContentDto();
        contentDto.setContentId(content.getContentId());
        contentDto.setTitle(content.getTitle());
        contentDto.setDescription(content.getDescription());
        contentDto.setGenres(content.getGenre());
        contentDto.setAgeRating(content.getAgeRating());
        return contentDto;
    }

    // Convert ContentDto to Content entity
    private Content mapToEntity(ContentDto contentDto) {
        Content content = new Content();
        content.setTitle(contentDto.getTitle());
        content.setDescription(contentDto.getDescription());
        content.setGenre(contentDto.getGenres());
        content.setAgeRating(contentDto.getAgeRating());
        return content;
    }
}
