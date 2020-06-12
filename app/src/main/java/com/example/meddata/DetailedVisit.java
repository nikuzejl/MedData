package com.example.meddata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailedVisit extends AppCompatActivity {
    private DatabaseReference ref;
    private TextView _date, _hospital, _doctor, _tests, _diagnosis, _treatment, _comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_visit);
        String addressValue = getIntent().getStringExtra("visitAddress");

        _date = findViewById(R.id.date);
        _hospital= findViewById(R.id.hospital);
        _doctor =  findViewById(R.id.doctor);
        _tests = findViewById(R.id.tests);
        _diagnosis = findViewById(R.id.diagnosis);
        _treatment = findViewById(R.id.treatment);
        _comments = findViewById(R.id.comments);

        ref = FirebaseDatabase.getInstance().getReference().child("forms").child(addressValue);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hospital = dataSnapshot.child("hospital").getValue().toString();
                String doctor = dataSnapshot.child("doctor").getValue().toString();
                String tests = dataSnapshot.child("tests").getValue().toString();
                String treatment= dataSnapshot.child("treatment").getValue().toString();
                String diagnosis = dataSnapshot.child("diagnosis").getValue().toString();
                String comments = dataSnapshot.child("comments").getValue().toString();
                String date = dataSnapshot.child("date").getValue().toString();

                _date.setText(date);
                _hospital.setText(hospital);
                _doctor.setText(doctor);
                _tests.setText(tests);
                _diagnosis.setText(diagnosis);
                _treatment.setText(treatment);
                _comments.setText(comments);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}


