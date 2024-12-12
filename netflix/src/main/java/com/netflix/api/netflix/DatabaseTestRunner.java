//package com.netflix.api.netflix;
//
//import com.netflix.api.netflix.models.*;
//import com.netflix.api.netflix.repository.ContentPreferenceRepository;
//import com.netflix.api.netflix.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//@Component
//public class DatabaseTestRunner implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final ContentPreference contentPreferenceRepository;
//
//    public DatabaseTestRunner(UserRepository userRepository, ContentPreference contentPreferenceRepository) {
//        this.userRepository = userRepository;
//        this.contentPreferenceRepository = contentPreferenceRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Subscription subscription = new Subscription(1, SubscriptionTier.HD, LocalDate.now(), LocalDate.now(), true);
//        // Create a new user
//        User user = new User(2, "testuser@example.com", "password123", subscription);
//        userRepository.save(user);
//
//        // Create ContentPreference for this user
//        ContentPreference contentPreference = new ContentPreference();
//        contentPreference.setUser(user); // Associate with the user
//        contentPreference.setPreferredGenres(Arrays.asList(Genre.ACTION, Genre.COMEDY));  // Preferred genres
//        contentPreference.setContentClassification(ContentClassification.MOVIE);  // Movie type
//        contentPreference.setPreferredQualities(Arrays.asList("HD", "4K"));  // Preferred qualities
//        contentPreference.setMinAgeRating(AgeRating.PG13);  // Minimum age rating for content
//
//        // Save ContentPreference
//        contentPreferenceRepository.save(contentPreference);
//
//        System.out.println("ContentPreference saved: " + contentPreference);
//    }
//}
