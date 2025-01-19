package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.ProfileDto;
import com.netflix.api.netflix.exception.ProfileNotFoundException;
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
    public ProfileServiceImpl(ProfileRepository profileRepository, UserRepository userRepository)
    {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProfileDto createProfile(int userId, ProfileDto profileDto)
    {
        // Fetch the user by ID to associate the profile with the user
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getProfiles().size() >= 4)
        {
            throw new IllegalArgumentException("Cannot create profiles");
        }

        // Convert ProfileDto to Profile entity
        Profile profile = mapToEntity(profileDto);
        profile.setUser(user);
        // Save the profile entity
        Profile savedProfile = this.profileRepository.save(profile);

        // Convert the saved profile entity to a ProfileDto and return it
        return mapToDto(savedProfile);
    }

    @Override
    public ProfileDto getProfileById(int profileId) throws ProfileNotFoundException
    {
        // Retrieve the profile by its ID
        Profile profile = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

        // Convert Profile entity to ProfileDto and return it
        return mapToDto(profile);
    }

    @Override
    public List<ProfileDto> getProfilesByName(String name) throws ProfileNotFoundException
    {
        List<Profile> profiles = this.profileRepository.findByName(name);

        if (profiles.isEmpty())
        {
            throw new ProfileNotFoundException("no profile found");
        }

        return profiles.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDto updateProfile(int profileId, ProfileDto profileDto)
    {
        // Retrieve the existing profile by ID
        Profile existingProfile = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // Update the profile fields with the data from the DTO
        existingProfile.setName(profileDto.getName());
        existingProfile.setAge(profileDto.getAge());
        existingProfile.setProfilePhotoUrl(profileDto.getProfilePhotoUrl());

        // Save the updated profile entity
        Profile updatedProfile = this.profileRepository.save(existingProfile);

        // Convert the updated profile entity to ProfileDto and return it
        return mapToDto(updatedProfile);
    }

    @Override
    public void deleteProfile(int profileId) throws ProfileNotFoundException
    {
        // Retrieve the profile by ID
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

        // Delete the profile entity
        profileRepository.delete(profile);
    }

    // Helper method to map Profile entity to ProfileDto
    private ProfileDto mapToDto(Profile profile)
    {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileId(profile.getProfileId());
        profileDto.setName(profile.getName());
        profileDto.setAge(profile.getAge());
        profileDto.setProfilePhotoUrl(profile.getProfilePhotoUrl());
        return profileDto;
    }

    // Helper method to map ProfileDto to Profile entity
    private Profile mapToEntity(ProfileDto profileDto)
    {
        Profile profile = new Profile();
        profile.setName(profileDto.getName());
        profile.setAge(profileDto.getAge());
        profile.setProfilePhotoUrl(profileDto.getProfilePhotoUrl());
        return profile;
    }
}
