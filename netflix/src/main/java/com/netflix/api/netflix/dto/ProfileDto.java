package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.ContentClassification;
import com.netflix.api.netflix.models.Language;
import lombok.Data;

import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Set;

@Data
public class ProfileDto
{
    private int profileId;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Min(value = 0, message = "Age must be non-negative")
    @Max(value = 150, message = "Age must be less than or equal to 150")
    private int age;

    @NotBlank(message = "Profile photo URL is required")
    @Size(max = 255, message = "Profile photo URL must be less than 255 characters")
    private String profilePhotoUrl;

    @NotNull(message = "Preferences list cannot be null")
    private List<@NotBlank(message = "Preference cannot be blank") String> preferences;

    @NotNull(message = "Language is required")
    private Language language;

    @NotNull(message = "Content classifications cannot be null")
    @Size(min = 1, message = "At least one content classification is required")
    private Set<ContentClassification> contentClassifications;

    private boolean interestedInSeries;
    private boolean interestedInFilms;
}

