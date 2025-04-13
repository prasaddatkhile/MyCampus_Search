package com.example.DataServices;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.Query;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirestoreService {

    public static Firestore db;

    public static void initializeFb(){
        try {
            FileInputStream serviceAccount = new FileInputStream("demo\\src\\main\\resources\\c2w-main-firebase-adminsdk-d4pte-58d9e7b0e7.json"); // Update with your actual path
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
                    .setCredentials(credentials)
                    .build();
            db = firestoreOptions.getService();
            db=FirestoreClient.getFirestore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static Firestore getFirestore() {
        return db;
    }
     public void addReview(String username, int rating, String review) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("'username' must be a non-empty String");
        }

        // Query Firestore to find the owner document(s) where 'username' matches
        CollectionReference ownersCollection = db.collection("owner");
        com.google.cloud.firestore.Query query = ownersCollection.whereEqualTo("username", username);

        try {
            // Execute the query synchronously
            ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();
            QuerySnapshot querySnapshot = querySnapshotFuture.get();

            // Iterate through the results (usually there should be only one matching document)
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Get the reference to the document and update it with the review data
                DocumentReference ownerRef = document.getReference();

                // Prepare the review data to be stored
                Map<String, Object> reviewData = new HashMap<>();
                reviewData.put("rating", rating);
                reviewData.put("review", review);

                // Update the document with the review data using merge option
                ownerRef.set(reviewData, SetOptions.merge());

                System.out.println("Review successfully added for owner with username: " + username);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error adding review: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        FirestoreService service = new FirestoreService();
        service.addReview("exampleUsername", 4, "This is a great institute!");
    }
    
}
