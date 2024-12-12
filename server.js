// Import the functions you need from the Firebase SDKs
import { initializeApp } from "firebase/app";
import { getFirestore, collection, doc, setDoc, Timestamp } from "firebase/firestore";

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyBLtOk_fNVquDnC-vv4GxbW_R5RYNNHeks",
    authDomain: "netflix-data-processing.firebaseapp.com",
    projectId: "netflix-data-processing",
    storageBucket: "netflix-data-processing.appspot.com",
    messagingSenderId: "937480604166",
    appId: "1:937480604166:web:c03a71d6232c4093f09187",
    measurementId: "G-JCCG9LESEQ"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const db = getFirestore(app); // Correct Firestore initialization

// Function to set up Firestore database
async function setupFirestore() {
    // USERS
    const userRef = doc(collection(db, "Users"), "user1");
    await setDoc(userRef, {
        email: "user@example.com",
        password: "hashed_password",
        subscriptionID: "sub1",
        isActivated: true,
        failedLoginAttempts: 0,
        accountLockUntil: Timestamp.now()
    });

    // PROFILES
    const profileRef = doc(collection(userRef, "Profiles"), "profile1");
    await setDoc(profileRef, {
        profilePhotoUrl:"localhost/img",
        name: "John Doe",
        language: "English",
        age: 25
    });

    // VIEWING HISTORY
    const viewingHistoryRef = doc(collection(profileRef, "ViewingHistory"), "history1");
    await setDoc(viewingHistoryRef, {
        contentID: "movie1",
        dateWatched: new Date(),
        durationWatched: 1200,
        resumePoint: 300
    });

    // VIEWING SESSIONS
    const viewingSessionRef = doc(collection(profileRef, "ViewingSession"), "session1");
    await setDoc(viewingSessionRef, {
        contentID: "movie1",
        startTime: new Date(),
        lastWatchedTime: new Date(),
        totalTime: 1800
    });

    // CONTENT
    const contentRef = doc(collection(db, "Content"), "movie1");
    await setDoc(contentRef, {
        title: "Example Movie",
        genreID: "genre1",
        duration: 7200,
        ageRating: "ageRating1",
        description: "A great movie",
        supportedQualities: ["HD", "UHD"]
    });

    const seriesRef = doc(collection(db, "Content"), "series1");
    await setDoc(seriesRef, {
        seasons: 1,
        title: "Example Series",
        genre_id: "genre2",
        description: "An amazing series"
    });

    const movieRef = doc(collection(db, "Content"), "movie1");
    await setDoc(movieRef, {
        title: "Example Series",
        duration: 4000,
        cast: ["actor1", "actor2"],
        releaseDate: new Date()
    });

    // EPISODES (Subcollection of Series Content)
    const episodeRef = doc(collection(seriesRef, "Episodes"), "episode1");
    await setDoc(episodeRef, {
        title: "Pilot",
        seasonNumber: 1,
        episodeNumber: 1,
        duration: 3600
    });

    // GENRES
    const genreRef = doc(collection(db, "Genre"), "genre1");
    await setDoc(genreRef, {
        name: "Action"
    });

    // SUBSCRIPTION
    const subscriptionRef = doc(collection(db, "Subscription"), "sub1");
    await setDoc(subscriptionRef, {
        tier: "HD",
        price: 12.99,
        billing_cycle: "monthly"
    });

    console.log("Firestore setup completed.");
}

// Execute the function
setupFirestore();
