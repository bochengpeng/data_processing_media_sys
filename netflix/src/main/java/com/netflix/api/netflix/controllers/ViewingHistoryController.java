package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.ViewingHistoryDto;
import com.netflix.api.netflix.dto.ViewingHistoryInternalDto;
import com.netflix.api.netflix.exception.EpisodeNotFoundException;
import com.netflix.api.netflix.exception.ProfileNotFoundException;
import com.netflix.api.netflix.exception.ViewingHistoryNotFoundException;
import com.netflix.api.netflix.services.ViewingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/viewing-history")
public class ViewingHistoryController
{

    @Autowired
    private ViewingHistoryService viewingHistoryService;

    @GetMapping("/{id}")
    public ResponseEntity<ViewingHistoryDto> getViewingHistoryById(@PathVariable int id) throws ViewingHistoryNotFoundException
    {
        ViewingHistoryDto viewingHistory = this.viewingHistoryService.getViewingHistoryById(id);

        return new ResponseEntity<>(viewingHistory, HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<List<ViewingHistoryDto>> getViewingHistoriesByProfileId(@PathVariable int id) throws ViewingHistoryNotFoundException, ProfileNotFoundException
    {
        List<ViewingHistoryDto> viewingHistory = this.viewingHistoryService.getViewingHistoriesByProfileId(id);

        return new ResponseEntity<>(viewingHistory, HttpStatus.OK);
    }

    @PostMapping("/create/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ViewingHistoryDto> createViewingHistory(@PathVariable(value = "id") int id, @RequestBody ViewingHistoryDto viewingHistory) throws ProfileNotFoundException, EpisodeNotFoundException
    {
        return new ResponseEntity<>(this.viewingHistoryService.createViewingHistory(id, viewingHistory), HttpStatus.CREATED);
    }

    // Update an existing ViewingHistory
    @PutMapping("/update/{id}/history/{vhid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ViewingHistoryDto> updateViewingHistory(
            @PathVariable(value = "id") int profileId,
            @PathVariable(value = "vhid") int vhId,
            @RequestBody ViewingHistoryInternalDto viewingHistory) throws ProfileNotFoundException {
        ViewingHistoryDto updatedViewingHistory = viewingHistoryService.updateViewingHistory(profileId, vhId, viewingHistory);
        return new ResponseEntity<>(updatedViewingHistory, HttpStatus.OK);
    }

    // Delete a ViewingHistory by ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteViewingHistory(@PathVariable(value = "id") int id) throws ViewingHistoryNotFoundException
    {
        viewingHistoryService.deleteViewingHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
