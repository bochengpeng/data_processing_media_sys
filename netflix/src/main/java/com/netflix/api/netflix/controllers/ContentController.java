package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.models.Content;
import com.netflix.api.netflix.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    @Autowired
    private ContentRepository contentRepository;

    @GetMapping
    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<Content> getContentById(@PathVariable int contentId) {
        return contentRepository.findById(contentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Content createContent(@RequestBody Content content) {
        return contentRepository.save(content);
    }

    @PutMapping("/{contentId}")
    public ResponseEntity<Content> updateContent(@PathVariable int contentId, @RequestBody Content contentDetails) {
        return contentRepository.findById(contentId)
                .map(content -> {
                    content.setTitle(contentDetails.getTitle());
                    content.setDescription(contentDetails.getDescription());
                    return ResponseEntity.ok(contentRepository.save(content));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> deleteContent(@PathVariable int contentId) {
        return contentRepository.findById(contentId)
                .map(content -> {
                    contentRepository.delete(content);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}