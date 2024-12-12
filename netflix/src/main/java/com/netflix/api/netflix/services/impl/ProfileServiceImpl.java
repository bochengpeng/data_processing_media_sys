package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.ProfileDto;
import com.netflix.api.netflix.models.Profile;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.ProfileRepository;
import com.netflix.api.netflix.repository.UserRepository;
import com.netflix.api.netflix.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService
{

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProfileDto createProfile(int userId, ProfileDto profileDto) {
        // Fetch the user by ID to associate the profile with the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert ProfileDto to Profile entity
        Profile profile = mapToEntity(profileDto);
//        profile.setUser(user); // Associate the profile with the user

        // Save the profile entity
        Profile savedProfile = profileRepository.save(profile);

        // Convert the saved profile entity to a ProfileDto and return it
        return mapToDto(savedProfile);
    }

    @Override
    public ProfileDto getProfileById(int profileId) {
        // Retrieve the profile by its ID
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // Convert Profile entity to ProfileDto and return it
        return mapToDto(profile);
    }

//    @Override
//    public List<ProfileDto> getProfilesByUserId(int userId)
//    {
//        return null;
//    }

//    @Override
//    public List<ProfileDto> getProfilesByUserId(int userId) {
//        // Retrieve all profiles associated with the user
//        List<Profile> profiles = profileRepository.findByUserId(userId);
//
//        // Convert Profile entities to ProfileDto and return the list
//        return profiles.stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public ProfileDto updateProfile(int profileId, ProfileDto profileDto) {
        // Retrieve the existing profile by ID
        Profile existingProfile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // Update the profile fields with the data from the DTO
        existingProfile.setName(profileDto.getName());
        existingProfile.setAge(profileDto.getAge());
        existingProfile.setProfilePhotoUrl(profileDto.getProfilePhotoUrl());

        // Save the updated profile entity
        Profile updatedProfile = profileRepository.save(existingProfile);

        // Convert the updated profile entity to ProfileDto and return it
        return mapToDto(updatedProfile);
    }

    @Override
    public void deleteProfile(int profileId) {
        // Retrieve the profile by ID
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // Delete the profile entity
        profileRepository.delete(profile);
    }

    // Helper method to map Profile entity to ProfileDto
    private ProfileDto mapToDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileId(profile.getProfileId());
        profileDto.setName(profile.getName());
        profileDto.setAge(profile.getAge());
        profileDto.setProfilePhotoUrl(profile.getProfilePhotoUrl());
        return profileDto;
    }

    // Helper method to map ProfileDto to Profile entity
    private Profile mapToEntity(ProfileDto profileDto) {
        Profile profile = new Profile();
        profile.setName(profileDto.getName());
        profile.setAge(profileDto.getAge());
        profile.setProfilePhotoUrl(profileDto.getProfilePhotoUrl());
        return profile;
    }
}
