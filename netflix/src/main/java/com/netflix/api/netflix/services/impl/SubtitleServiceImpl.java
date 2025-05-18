package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.SubtitleDto;
import com.netflix.api.netflix.exception.ContentNotFoundException;
import com.netflix.api.netflix.exception.SubtitleNotFoundException;
import com.netflix.api.netflix.models.Content;
import com.netflix.api.netflix.models.Subtitle;
import com.netflix.api.netflix.repositories.ContentRepository;
import com.netflix.api.netflix.repositories.SubtitleRepository;
import com.netflix.api.netflix.services.SubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubtitleServiceImpl implements SubtitleService
{

    @Autowired
    private SubtitleRepository subtitleRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Transactional
    @Override
    public SubtitleDto createSubtitle(SubtitleDto dto) throws ContentNotFoundException
    {
        try
        {
            Content content = this.contentRepository.findById(dto.getContentId())
                    .orElseThrow(() -> new ContentNotFoundException("Content not found"));

            Subtitle subtitle = mapToEntity(dto, content);
            subtitle.setContent(content);
            Subtitle saved = this.subtitleRepository.save(subtitle);

            return mapToDto(saved);
        } catch (ContentNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create subtitle", e);
        }
    }

    @Override
    public List<SubtitleDto> getSubtitlesByContentId(int contentId)
    {
        try
        {
            return this.subtitleRepository.findByContentId(contentId).stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to fetch subtitles for contentId: " + contentId, e);
        }
    }

    @Override
    public SubtitleDto updateSubtitle(int id, SubtitleDto dto) throws SubtitleNotFoundException
    {
        try
        {
            Subtitle subtitle = this.subtitleRepository.findById(id)
                    .orElseThrow(() -> new SubtitleNotFoundException("Subtitle not found"));

            subtitle.setLanguage(dto.getLanguage());
            subtitle.setFilePath(dto.getFilePath());

            Subtitle updated = this.subtitleRepository.save(subtitle);
            return mapToDto(updated);
        } catch (SubtitleNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to update subtitle", e);
        }
    }

    @Override
    public void deleteSubtitle(int id) throws SubtitleNotFoundException
    {
        try
        {
            Subtitle subtitle = this.subtitleRepository.findById(id)
                    .orElseThrow(() -> new SubtitleNotFoundException("Subtitle not found"));
            this.subtitleRepository.delete(subtitle);
        } catch (SubtitleNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to delete subtitle", e);
        }
    }

    private SubtitleDto mapToDto(Subtitle subtitle)
    {
        SubtitleDto dto = new SubtitleDto();
        dto.setId(subtitle.getId());
        if (subtitle.getContent() != null)
        {
            dto.setContentId(subtitle.getContent().getId());
        }
        dto.setLanguage(subtitle.getLanguage());
        dto.setFilePath(subtitle.getFilePath());

        return dto;
    }

    private Subtitle mapToEntity(SubtitleDto dto, Content content)
    {
        Subtitle subtitle = new Subtitle();
        subtitle.setId(dto.getId());
        subtitle.setContent(content);
        subtitle.setLanguage(dto.getLanguage());
        subtitle.setFilePath(dto.getFilePath());

        return subtitle;
    }
}