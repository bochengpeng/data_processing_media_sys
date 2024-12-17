package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.models.ViewingHistory;
import com.netflix.api.netflix.repository.ViewingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viewing-history")
public class ViewingHistoryController
{

    @Autowired
    private ViewingHistoryRepository viewingHistoryRepository;

    @GetMapping
    public List<ViewingHistory> getAllViewingHistories()
    {
        return viewingHistoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewingHistory> getViewingHistoryById(@PathVariable int id)
    {
        return viewingHistoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ViewingHistory createViewingHistory(@RequestBody ViewingHistory viewingHistory)
    {
        return viewingHistoryRepository.save(viewingHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViewingHistory> updateViewingHistory(@PathVariable int id, @RequestBody ViewingHistory updatedHistory)
    {
        return viewingHistoryRepository.findById(id).map(existingHistory ->
        {
            existingHistory.setMovie(updatedHistory.getMovie());
            existingHistory.setStopAt(updatedHistory.getStopAt());
            return ResponseEntity.ok(viewingHistoryRepository.save(existingHistory));
        }).orElse(ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteViewingHistory(@PathVariable Long id) {
//        return viewingHistoryRepository.findById(id).map(history -> {
//            viewingHistoryRepository.delete(history);
//            return ResponseEntity.noContent().build();
//        }).orElse(ResponseEntity.notFound().build());
//    }
}
