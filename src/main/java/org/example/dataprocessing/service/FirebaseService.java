package org.example.dataprocessing.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseService
{
    private static Firestore firestore;

    public static void initializeFirebase() throws IOException
    {
        if (FirebaseApp.getApps().isEmpty())
        {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://your-database-url.firebaseio.com") // Replace with your Firestore database URL
                    .build();

            FirebaseApp.initializeApp(options);
        }

        firestore = FirestoreClient.getFirestore();
    }

    public static Firestore getFirestore()
    {
        return firestore;
    }
}
