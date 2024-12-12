package org.example.dataprocessing.service;

import org.example.dataprocessing.entity.ContentPreference;
import org.example.dataprocessing.repository.ContentPreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentPreferenceService
{

    private final ContentPreferenceRepository contentPreferenceRepository;

    public ContentPreferenceService(ContentPreferenceRepository contentPreferenceRepository)
    {
        this.contentPreferenceRepository = contentPreferenceRepository;
    }

    // Create or save a new ContentPreference
    public ContentPreference createContentPreference(ContentPreference contentPreference)
    {
        return contentPreferenceRepository.save(contentPreference);
    }

    // Get a ContentPreference by its ID
    public Optional<ContentPreference> getContentPreferenceById(Long preferenceId)
    {
        return contentPreferenceRepository.findById(preferenceId);
    }

    // Get ContentPreferences by Profile ID
    public List<ContentPreference> getContentPreferencesByProfileId(Long profileId)
    {
        return contentPreferenceRepository.findByProfileId(profileId);
    }

    // Update an existing ContentPreference
    public ContentPreference updateContentPreference(Long preferenceId, ContentPreference updatedPreference)
    {
        return contentPreferenceRepository.findById(preferenceId).map(existingPreference ->
        {
            existingPreference.setPreferredGenres(updatedPreference.getPreferredGenres());
            existingPreference.setPreferredQualities(updatedPreference.getPreferredQualities());
            existingPreference.setMinAgeRating(updatedPreference.getMinAgeRating());
            existingPreference.setContentClassification(updatedPreference.getContentClassification());
            return contentPreferenceRepository.save(existingPreference);
        }).orElseThrow(() -> new IllegalArgumentException("ContentPreference not found with ID: " + preferenceId));
    }

    // Delete a ContentPreference by ID
    public void deleteContentPreference(Long preferenceId)
    {
        if (!contentPreferenceRepository.existsById(preferenceId))
        {
            throw new IllegalArgumentException("ContentPreference not found with ID: " + preferenceId);
        }
        contentPreferenceRepository.deleteById(preferenceId);
    }
}
