package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.Profile;
import org.example.dataprocessing.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService
{

    private final ProfileRepository profileRepository;

    // Constructor Injection
    public ProfileService(ProfileRepository profileRepository)
    {
        this.profileRepository = profileRepository;
    }

    // Create a new profile
    public Profile createProfile(Profile profile)
    {
        return profileRepository.save(profile);
    }

    // Get a profile by its ID
    public Optional<Profile> getProfileById(Long profileId)
    {
        return profileRepository.findById(profileId);
    }

    // Fetch all profiles
    public List<Profile> getAllProfiles()
    {
        return profileRepository.findAll();
    }

    // Update a profile
    public Profile updateProfile(Long profileId, Profile updatedProfile)
    {
        return profileRepository.findById(profileId).map(existingProfile ->
        {
            existingProfile.setName(updatedProfile.getName());
            existingProfile.setAge(updatedProfile.getAge());
            existingProfile.setProfilePhotoUrl(updatedProfile.getProfilePhotoUrl());
            existingProfile.setLanguage(updatedProfile.getLanguage());
            existingProfile.setPreferences(updatedProfile.getPreferences());
            existingProfile.setWatchList(updatedProfile.getWatchList());
            existingProfile.setViewingHistory(updatedProfile.getViewingHistory());
            return profileRepository.save(existingProfile);
        }).orElseThrow(() -> new IllegalArgumentException("Profile not found with ID: " + profileId));
    }

    // Delete a profile by its ID
    public void deleteProfile(Long profileId)
    {
        if (!profileRepository.existsById(profileId))
        {
            throw new IllegalArgumentException("Profile not found with ID: " + profileId);
        }
        profileRepository.deleteById(profileId);
    }

    // Fetch profiles by name
    public List<Profile> getProfilesByName(String name)
    {
        return profileRepository.findAll()
                .stream()
                .filter(profile -> profile.getName().equalsIgnoreCase(name))
                .toList();
    }
}
