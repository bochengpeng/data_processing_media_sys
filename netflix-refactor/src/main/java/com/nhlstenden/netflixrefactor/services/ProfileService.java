package com.nhlstenden.netflixrefactor.services;

import com.nhlstenden.netflixrefactor.dtos.ProfileDto;

import java.util.List;

public interface ProfileService {
    ProfileDto createProfile(ProfileDto dto);
    ProfileDto getProfileById(Long id);
    List<ProfileDto> getProfilesByUserId(Long userId);
    ProfileDto updateProfile(Long id, ProfileDto dto);
    void deleteProfile(Long id);
}

