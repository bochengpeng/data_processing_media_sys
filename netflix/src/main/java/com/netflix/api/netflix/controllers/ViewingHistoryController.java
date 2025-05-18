package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.ViewingHistoryDto;
import com.netflix.api.netflix.exception.EpisodeNotFoundException;
import com.netflix.api.netflix.exception.ProfileNotFoundException;
import com.netflix.api.netflix.exception.ViewingHistoryNotFoundException;
import com.netflix.api.netflix.services.ViewingHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/viewing-history")
public class ViewingHistoryController
{

    private final ViewingHistoryService viewingHistoryService;

    @Autowired
    public ViewingHistoryController(ViewingHistoryService viewingHistoryService)
    {
        this.viewingHistoryService = viewingHistoryService;
    }

    // Get Viewing History by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getViewingHistoryById(@PathVariable int id)
    {
        try
        {
            ViewingHistoryDto history = this.viewingHistoryService.getViewingHistoryById(id);
            return ResponseEntity.ok(history);
        } catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("Error retrieving history: " + e.getMessage());
        }
    }

    // Get all histories by Profile ID
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getViewingHistoriesByProfileId(@PathVariable int id)
    {
        try
        {
            List<ViewingHistoryDto> histories = this.viewingHistoryService.getViewingHistoriesByProfileId(id);
            return ResponseEntity.ok(histories);
        } catch (ViewingHistoryNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("Failed to retrieve histories: " + e.getMessage());
        }
    }

    // Create a new Viewing History
    @PostMapping("/create")
    public ResponseEntity<?> createViewingHistory(
//            @PathVariable("profileId") int profileId,
            @Valid @RequestBody ViewingHistoryDto viewingHistoryDto,
            BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            ViewingHistoryDto created = this.viewingHistoryService.createViewingHistory(viewingHistoryDto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

    // Update existing Viewing History
    @PutMapping("/update/history/{historyId}")
    public ResponseEntity<?> updateViewingHistory(
//            @PathVariable("profileId") int profileId,
            @PathVariable("historyId") int historyId,
            @Valid @RequestBody ViewingHistoryDto viewingHistoryDto,
            BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            ViewingHistoryDto updated = this.viewingHistoryService.updateViewingHistory(historyId, viewingHistoryDto);
            return ResponseEntity.ok(updated);
        } catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("Failed to update history: " + e.getMessage());
        }
    }

    // Delete Viewing History
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteViewingHistory(@PathVariable int id)
    {
        try
        {
            this.viewingHistoryService.deleteViewingHistory(id);
            return ResponseEntity.ok("Viewing history deleted successfully");
        } catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("Error deleting history: " + e.getMessage());
        }
    }
}