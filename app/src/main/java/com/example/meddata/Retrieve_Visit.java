package com.example.meddata;

import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class Retrieve_Visit extends AppCompatActivity {
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private VisitAdapter visitAdapter;
    private ChildEventListener mChildEventListener;

    private ListView allVisits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_visit);
        allVisits = (ListView) findViewById(R.id.all_visits);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference().child("forms");

        List<Visit> visits_list = new ArrayList<>();
        visitAdapter= new VisitAdapter(this, R.layout.retrieve_visit, visits_list);
        allVisits.setAdapter(visitAdapter);

        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Visit visit = dataSnapshot.getValue(Visit.class);
                    visitAdapter.add(visit);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            };
            dbRef.addChildEventListener(mChildEventListener);
        }
    }
}

