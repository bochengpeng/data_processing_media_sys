package com.netflix.api.netflix.services;


import com.netflix.api.netflix.dto.ProfileDto;
import com.netflix.api.netflix.exception.ProfileNotFoundException;

import java.util.List;

public interface ProfileService {
    ProfileDto createProfile(int userId, ProfileDto profileDto);
    ProfileDto getProfileById(int profileId) throws ProfileNotFoundException;
    List<ProfileDto> getProfilesByUserId(int userId) throws ProfileNotFoundException;
    List<ProfileDto> getProfilesByName(String name) throws ProfileNotFoundException;
    ProfileDto updateProfile(int profileId, ProfileDto profileDto);
    void deleteProfile(int profileId) throws ProfileNotFoundException;
}

