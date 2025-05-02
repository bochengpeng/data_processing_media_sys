package com.nhlstenden.netflixrefactor.controller;

import com.nhlstenden.netflixrefactor.dtos.ContentDto;
import com.nhlstenden.netflixrefactor.models.Content;
import com.nhlstenden.netflixrefactor.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping("/upload")
    public ResponseEntity<Content> createContent(@RequestBody ContentDto contentDto) {
        Content content = contentService.createContent(contentDto);
        return new ResponseEntity<>(content, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Content>> getAllContent() {
        return ResponseEntity.ok(contentService.getAllContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.getContentById(id));
    }
}

