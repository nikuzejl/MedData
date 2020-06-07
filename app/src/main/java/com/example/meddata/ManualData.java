package com.example.meddata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class ManualData extends AppCompatActivity {
    private Button button_save;
    private FirebaseDatabase myFirebaseDatabase;
    private DatabaseReference typedVisits;
    private DatabaseReference docVisits;
    private EditText Tdate, Thospital, Tdoctor, Ttests, Tdiagnosis, Ttreatment, Tcomments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_data);
        ///*
        myFirebaseDatabase = FirebaseDatabase.getInstance();
        typedVisits = myFirebaseDatabase.getReference().child("forms");
        docVisits = myFirebaseDatabase.getReference().child("documents");

        button_save = findViewById(R.id.save_button);

        Tdate = findViewById(R.id.date);
        Thospital= findViewById(R.id.hospital);
        Tdoctor =  findViewById(R.id.doctor);
        Ttests = findViewById(R.id.tests);
        Tdiagnosis = findViewById(R.id.diagnosis);
        Ttreatment = findViewById(R.id.treatment);
        Tcomments = findViewById(R.id.comments);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Visit visit = new Visit(Tdate.getText().toString(), Thospital.getText().toString(), Tdoctor.getText().toString(), Ttests.getText().toString(), Tdiagnosis.getText().toString(), Ttreatment.getText().toString(), Tcomments.getText().toString());
                typedVisits.push().setValue(visit);

                // Clear input boxes
                Thospital.setText("");
                Tdoctor.setText("");
                Ttests.setText("");
                Tdiagnosis.setText("");
                Ttreatment.setText("");
                Tcomments.setText("");;
                Tdate.setText("");
            }
        });
    }
}

