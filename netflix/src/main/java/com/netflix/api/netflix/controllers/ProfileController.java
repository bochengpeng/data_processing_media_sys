package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.ProfileDto;
import com.netflix.api.netflix.exception.ProfileNotFoundException;
import com.netflix.api.netflix.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/profile")
public class ProfileController
{
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable int profileId) throws ProfileNotFoundException
    {
        ProfileDto profile = this.profileService.getProfileById(profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/name/{profileName}")
    public ResponseEntity<List<ProfileDto>> getProfilesByName(@PathVariable(value = "profileName") String profileName) throws ProfileNotFoundException
    {
        List<ProfileDto> profiles = this.profileService.getProfilesByName(profileName);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PostMapping("/create/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProfileDto> createProfile(@PathVariable(value = "userId") int userId, @RequestBody ProfileDto profileDto)
    {
        return new ResponseEntity<>(this.profileService.createProfile(userId, profileDto), HttpStatus.CREATED);
    }

    @PutMapping("/{profileId}/update")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable(value = "profileId") int profileId, @RequestBody ProfileDto profileDto)
    {
        ProfileDto updatedProfile = this.profileService.updateProfile(profileId, profileDto);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{profileId}/delete")
    public ResponseEntity<String> deleteProfile(@PathVariable(value = "profileId") int profileId) throws ProfileNotFoundException
    {
        this.profileService.deleteProfile(profileId);
        return new ResponseEntity<>("Profile deleted successfully", HttpStatus.OK);
    }
}