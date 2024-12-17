package com.netflix.api.netflix.services;


import com.netflix.api.netflix.dto.ProfileDto;
import com.netflix.api.netflix.models.Language;
import com.netflix.api.netflix.models.Profile;
import com.netflix.api.netflix.models.User;
import com.netflix.api.netflix.repository.ProfileRepository;
import com.netflix.api.netflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//public interface ProfileService
//{

//    @Autowired
//    private ProfileRepository profileRepository;
//    private UserRepository userRepository;
//
//    public Profile createProfile(int userId, Profile profile)
//    {
//        // Fetch the user by userId
//        Optional<User> userOptional = userRepository.findById(userId);
//
//        if (userOptional.isEmpty())
//        {
//            throw new IllegalArgumentException("User with ID " + userId + " not found");
//        }
//
//        // Associate the profile with the fetched user
////        User user = userOptional.get();
//////        profile.setUser(user);
////        Profile profile1 = new Profile();
////        user.setProfiles((List<Profile>) profile1);
//
//        // Save the profile to the repository
//        return profileRepository.save(profile);
//    }
//
//
//    public Profile getProfile(int profileId)
//    {
//        // Logic to fetch profile
//        return profileRepository.findById(profileId).orElseThrow();
//    }
//
//    public List<Profile> getProfilesByLanguage(Language language)
//    {
//        return profileRepository.findByLanguage(language);
//    }
//}

public interface ProfileService {
    ProfileDto createProfile(int userId, ProfileDto profileDto);
    ProfileDto getProfileById(int profileId);
//    List<ProfileDto> getProfilesByUserId(int userId);
    ProfileDto updateProfile(int profileId, ProfileDto profileDto);
    void deleteProfile(int profileId);
}

