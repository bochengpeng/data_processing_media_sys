package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.SubtitleDto;
import com.netflix.api.netflix.exception.ContentNotFoundException;
import com.netflix.api.netflix.exception.SubtitleNotFoundException;
import com.netflix.api.netflix.services.SubtitleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/subtitles")
@Validated
public class SubtitleController
{
    private final SubtitleService subtitleService;

    @Autowired
    public SubtitleController(SubtitleService subtitleService)
    {
        this.subtitleService = subtitleService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSubtitle(@Valid @RequestBody SubtitleDto dto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            SubtitleDto created = this.subtitleService.createSubtitle(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (ContentNotFoundException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<List<SubtitleDto>> getSubtitlesByContentId(@PathVariable int contentId)
    {
        List<SubtitleDto> subtitles = this.subtitleService.getSubtitlesByContentId(contentId);
        return new ResponseEntity<>(subtitles, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateSubtitle(@PathVariable int id, @Valid @RequestBody SubtitleDto dto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            SubtitleDto updated = this.subtitleService.updateSubtitle(id, dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (SubtitleNotFoundException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteSubtitle(@PathVariable int id)
    {
        try
        {
            this.subtitleService.deleteSubtitle(id);
            return new ResponseEntity<>("Subtitle deleted successfully", HttpStatus.OK);
        } catch (SubtitleNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
