package com.nhlstenden.netflixrefactor.controller;

import com.nhlstenden.netflixrefactor.dtos.ProfileDto;
import com.nhlstenden.netflixrefactor.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.createProfile(profileDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProfileDto>> getProfilesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(profileService.getProfilesByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable Long id, @RequestBody ProfileDto dto) {
        return ResponseEntity.ok(profileService.updateProfile(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}

