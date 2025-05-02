package com.nhlstenden.netflixrefactor.services.serviceimpl;

import com.nhlstenden.netflixrefactor.dtos.ProfileDto;
import com.nhlstenden.netflixrefactor.models.Profile;
import com.nhlstenden.netflixrefactor.models.User;
import com.nhlstenden.netflixrefactor.repositories.ProfileRepository;
import com.nhlstenden.netflixrefactor.repositories.UserRepository;
import com.nhlstenden.netflixrefactor.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService
{

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ProfileDto createProfile(ProfileDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = Profile.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .language(dto.getLanguage())
                .profilePhoto(dto.getProfilePhoto())
                .preferredGenres(dto.getPreferredGenres())
                .interestedInFilms(dto.isInterestedInFilms())
                .interestedInSeries(dto.isInterestedInSeries())
                .minimumAge(dto.getMinimumAge())
                .viewingClassifications(dto.getViewingClassifications())
                .user(user)
                .build();

        Profile saved = profileRepository.save(profile);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public ProfileDto getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return mapToDto(profile);
    }

    @Override
    public List<ProfileDto> getProfilesByUserId(Long userId) {
        List<Profile> profiles = profileRepository.findByUserId(userId);
        return profiles.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ProfileDto updateProfile(Long id, ProfileDto dto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setName(dto.getName());
        profile.setAge(dto.getAge());
        profile.setLanguage(dto.getLanguage());
        profile.setProfilePhoto(dto.getProfilePhoto());
        profile.setPreferredGenres(dto.getPreferredGenres());
        profile.setInterestedInFilms(dto.isInterestedInFilms());
        profile.setInterestedInSeries(dto.isInterestedInSeries());
        profile.setMinimumAge(dto.getMinimumAge());
        profile.setViewingClassifications(dto.getViewingClassifications());

        return mapToDto(profileRepository.save(profile));
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    private ProfileDto mapToDto(Profile p) {
        return ProfileDto.builder()
                .id(p.getId())
                .name(p.getName())
                .age(p.getAge())
                .language(p.getLanguage())
                .profilePhoto(p.getProfilePhoto())
                .preferredGenres(p.getPreferredGenres())
                .interestedInFilms(p.isInterestedInFilms())
                .interestedInSeries(p.isInterestedInSeries())
                .minimumAge(p.getMinimumAge())
                .viewingClassifications(p.getViewingClassifications())
                .userId(p.getUser().getId())
                .build();
    }
}

