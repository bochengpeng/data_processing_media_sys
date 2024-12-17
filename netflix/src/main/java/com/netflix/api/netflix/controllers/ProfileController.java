package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.models.Profile;
import com.netflix.api.netflix.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController
{
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping
    public List<Profile> getAllProfiles()
    {
        return profileRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable int id)
    {
        return profileRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Profile createProfile(@RequestBody Profile profile)
    {
        return profileRepository.save(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable int id, @RequestBody Profile profileDetails)
    {
        return profileRepository.findById(id)
                .map(profile ->
                {
                    profile.setName(profileDetails.getName());
                    profile.setAge(profileDetails.getAge());
                    return ResponseEntity.ok(profileRepository.save(profile));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int id)
    {
        return profileRepository.findById(id)
                .map(profile ->
                {
                    profileRepository.delete(profile);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}