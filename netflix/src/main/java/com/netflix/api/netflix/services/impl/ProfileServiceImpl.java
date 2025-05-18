package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.ProfileDto;
import com.netflix.api.netflix.exception.ProfileNotFoundException;
import com.netflix.api.netflix.exception.UserNotFoundException;
import com.netflix.api.netflix.models.Profile;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repositories.ProfileRepository;
import com.netflix.api.netflix.repositories.UserRepository;
import com.netflix.api.netflix.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public ProfileDto createProfile(int userId, ProfileDto profileDto)
    {
        try
        {
            User user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            if (user.getProfiles().size() >= 4)
            {
                throw new IllegalArgumentException("Cannot create more than 4 profiles for a user");
            }

            Profile profile = mapToEntity(profileDto);
            profile.setUser(user);
            Profile savedProfile = this.profileRepository.save(profile);

            return mapToDto(savedProfile);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create profile: " + e.getMessage(), e);
        }
    }

    @Override
    public ProfileDto getProfileById(int profileId) throws ProfileNotFoundException
    {
        try
        {
            Profile profile = this.profileRepository.findById(profileId)
                    .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

            return mapToDto(profile);
        } catch (ProfileNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to get profile by id: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ProfileDto> getProfilesByUserId(int userId) throws ProfileNotFoundException
    {
        try
        {
            List<Profile> profiles = this.profileRepository.findByUserId(userId);

            if (profiles.isEmpty())
            {
                throw new ProfileNotFoundException("No profiles found for user");
            }

            return profiles.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (ProfileNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to get profiles by user id: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ProfileDto> getProfilesByName(String name) throws ProfileNotFoundException
    {
        try
        {
            List<Profile> profiles = this.profileRepository.findByName(name);

            if (profiles.isEmpty())
            {
                throw new ProfileNotFoundException("No profiles found with the given name");
            }

            return profiles.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (ProfileNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to get profiles by name: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public ProfileDto updateProfile(int profileId, ProfileDto profileDto)
    {
        try
        {
            Profile existingProfile = this.profileRepository.findById(profileId)
                    .orElseThrow(() -> new RuntimeException("Profile not found"));

            existingProfile.setName(profileDto.getName());
            existingProfile.setAge(profileDto.getAge());
            existingProfile.setProfilePhotoUrl(profileDto.getProfilePhotoUrl());
            existingProfile.setPreferences(profileDto.getPreferences());
            existingProfile.setLanguage(profileDto.getLanguage());
            existingProfile.setContentClassifications(profileDto.getContentClassifications());
            existingProfile.setInterestedInSeries(profileDto.isInterestedInSeries());
            existingProfile.setInterestedInFilms(profileDto.isInterestedInFilms());

            Profile updatedProfile = this.profileRepository.save(existingProfile);
            return mapToDto(updatedProfile);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to update profile: " + e.getMessage(), e);
        }
    }


    @Transactional
    @Override
    public void deleteProfile(int profileId) throws ProfileNotFoundException
    {
        try
        {
            Profile profile = this.profileRepository.findById(profileId)
                    .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

            this.profileRepository.delete(profile);
        } catch (ProfileNotFoundException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to delete profile: " + e.getMessage(), e);
        }
    }

    // Mapping methods
    private ProfileDto mapToDto(Profile profile)
    {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileId(profile.getProfileId());
        profileDto.setName(profile.getName());
        profileDto.setAge(profile.getAge());
        profileDto.setProfilePhotoUrl(profile.getProfilePhotoUrl());
        profileDto.setPreferences(profile.getPreferences());
        profileDto.setLanguage(profile.getLanguage());
        profileDto.setContentClassifications(profile.getContentClassifications());
        profileDto.setInterestedInSeries(profile.isInterestedInSeries());
        profileDto.setInterestedInFilms(profile.isInterestedInFilms());

        return profileDto;
    }

    private Profile mapToEntity(ProfileDto profileDto)
    {
        Profile profile = new Profile();
        profile.setName(profileDto.getName());
        profile.setAge(profileDto.getAge());
        profile.setProfilePhotoUrl(profileDto.getProfilePhotoUrl());
        profile.setPreferences(profileDto.getPreferences());
        profile.setLanguage(profileDto.getLanguage());
        profile.setContentClassifications(profileDto.getContentClassifications());
        profile.setInterestedInSeries(profileDto.isInterestedInSeries());
        profile.setInterestedInFilms(profileDto.isInterestedInFilms());

        return profile;
    }
}