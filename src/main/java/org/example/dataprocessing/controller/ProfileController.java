package org.example.dataprocessing.controller;

import org.example.dataprocessing.entity.Profile;
import org.example.dataprocessing.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/{userId}")
    public ResponseEntity<Profile> createProfile(@PathVariable Long userId, @RequestBody Profile profile) {
        Profile createdProfile = profileService.createProfile(userId, profile);
        return ResponseEntity.ok(createdProfile);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long profileId) {
        Profile profile = profileService.getProfile(profileId);
        return ResponseEntity.ok(profile);
    }
}