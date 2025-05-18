package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.WatchListDto;
import com.netflix.api.netflix.services.WatchListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/watchlist")
@Validated
public class WatchlistController
{

    @Autowired
    private WatchListService watchListService;

    @PostMapping("/add")
    public ResponseEntity<?> addToWatchList(@Valid @RequestBody WatchListDto dto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            WatchListDto saved = this.watchListService.addToWatchList(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>("Error adding to watchlist: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<?> getWatchListByProfile(@PathVariable int profileId)
    {
        try
        {
            List<WatchListDto> list = this.watchListService.getWatchListByProfileId(profileId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>("Profile not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{watchListId}")
    public ResponseEntity<?> getWatchListItemById(@PathVariable int watchListId)
    {
        try
        {
            WatchListDto dto = this.watchListService.getWatchListItemById(watchListId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>("Item not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{watchListId}/update")
    public ResponseEntity<?> updateWatchListItem(
            @PathVariable int watchListId,
            @Valid @RequestBody WatchListDto dto,
            BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }

        try
        {
            WatchListDto updated = this.watchListService.updateWatchListItem(watchListId, dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>("Update failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{watchListId}/remove")
    public ResponseEntity<?> removeFromWatchList(@PathVariable int watchListId)
    {
        try
        {
            this.watchListService.removeFromWatchList(watchListId);
            return new ResponseEntity<>("Watchlist item removed successfully", HttpStatus.OK);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>("Item not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

