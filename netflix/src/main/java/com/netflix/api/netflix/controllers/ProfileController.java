package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.ProfileDto;
import com.netflix.api.netflix.exception.ProfileNotFoundException;
import com.netflix.api.netflix.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/profiles")
public class ProfileController
{
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService)
    {
        this.profileService = profileService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<?> getProfileById(@PathVariable int profileId)
    {
        try
        {
            ProfileDto profile = this.profileService.getProfileById(profileId);
            return ResponseEntity.ok(profile);
        } catch (ProfileNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/search/name/{profileName}")
    public ResponseEntity<?> getProfilesByName(@PathVariable String profileName)
    {
        try
        {
            List<ProfileDto> profiles = this.profileService.getProfilesByName(profileName);
            return ResponseEntity.ok(profiles);
        } catch (ProfileNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getProfilesByUserId(@PathVariable int userId)
    {
        try
        {
            List<ProfileDto> profiles = this.profileService.getProfilesByUserId(userId);
            return ResponseEntity.ok(profiles);
        } catch (ProfileNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createProfile(@PathVariable int userId, @Valid @RequestBody ProfileDto profileDto)
    {
        try
        {
            ProfileDto created = this.profileService.createProfile(userId, profileDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create profile: " + ex.getMessage());
        }
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<?> updateProfile(@PathVariable int profileId, @Valid @RequestBody ProfileDto profileDto)
    {
        try
        {
            ProfileDto updated = this.profileService.updateProfile(profileId, profileDto);
            return ResponseEntity.ok(updated);
        } catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable int profileId)
    {
        try
        {
            this.profileService.deleteProfile(profileId);
            return ResponseEntity.ok("Profile deleted successfully");
        } catch (ProfileNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}