package com.example.meddata;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.database.DataSnapshot;

public class Retrieve_Visit extends AppCompatActivity {

    //DownloadButton;

    StorageReference mStorageRef;
    DatabaseReference reff;
    private static final String TAG = "MainActivity";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    private String userId;
    DataSnapshot mDataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_visit);

//    // Write a message to the database
//        mDatabase = FirebaseDatabase.getInstance();
//        mDbRef = mDatabase.getReference("forms");
//
//    //Setting firebase unique key for Hashmap list
//        String userId = mDbRef.push().getKey();
//    // creating user object
//        Visit visit = new Visit("WHATTTTTTTTTT", "hillary@xyz.com", "gfagag", "Tokyo", "fagagag", "fagqgq", "fagqhqh");
//
//        mDbRef.child(userId).setValue(visit);
//
//        for (DataSnapshot child : mDataSnapshot.getChildren()) {
//            Log.i(TAG, child.getKey());
//            Log.i(TAG, child.getValue(String.class));
//        }
    }
}

