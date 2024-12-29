package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.dto.ProfileDto;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netflix/user")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{userId}/profiles/{profileId}")
    public ResponseEntity<ProfileDto> getProfileById(
            @PathVariable int profileId,
            @PathVariable int userId)
             throws UserNotFoundException
    {
        ProfileDto profile = profileService.getProfileById(profileId, userId);
        return ResponseEntity.ok(profile);
    }

//    @GetMapping
//    public List<Profile> getAllProfiles()
//    {
//        return profileRepository.findAll();
//    }

//    @GetMapping("/user/{userId}/profiles/{id}")
//    public ResponseEntity<ProfileDto> getProfileById(@PathVariable(value = "id") int id, @PathVariable(value = "userId") int userId)
//    {
//        ProfileDto profileDto = profileService.getProfileById(id, userId);
//        return new ResponseEntity<>(profileDto, HttpStatus.OK);
//    }

//    @PostMapping
//    public Profile createProfile(@RequestBody Profile profile)
//    {
//        return profileRepository.save(profile);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Profile> updateProfile(@PathVariable int id, @RequestBody Profile profileDetails)
//    {
//        return profileRepository.findById(id)
//                .map(profile ->
//                {
//                    profile.setName(profileDetails.getName());
////                    profile.setAge(profileDetails.getAge());
//                    return ResponseEntity.ok(profileRepository.save(profile));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProfile(@PathVariable int id)
//    {
//        return profileRepository.findById(id)
//                .map(profile ->
//                {
//                    profileRepository.delete(profile);
//                    return ResponseEntity.ok().<Void>build();
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
}