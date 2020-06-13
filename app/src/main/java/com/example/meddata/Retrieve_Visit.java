package com.example.meddata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.ArrayList;
import java.util.List;

public class Retrieve_Visit extends AppCompatActivity {
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private FirebaseFirestore dbFS = FirebaseFirestore.getInstance();
    private New_VisitAdapter new_visitAdapter;
    private CollectionReference dbFScollections = dbFS.collection("forms");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_visit);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = dbFScollections.orderBy("date", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Visit> options = new FirestoreRecyclerOptions.Builder<Visit>()
                .setQuery(query, Visit.class)
                .build();

        new_visitAdapter = new New_VisitAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new_visitAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                new_visitAdapter .deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

        new_visitAdapter.setOnItemClickListener(new New_VisitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Visit visit = documentSnapshot.toObject(Visit.class);
                String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
               // Toast.makeText(Retrieve_Visit.this,
               //         "Position: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();
                displayVisit(id);
            }
        });
    }

    public void displayVisit(String s){
        Intent intent = new Intent(this, DetailedVisit.class);
        intent.putExtra("visitAddress", s);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new_visitAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        new_visitAdapter.stopListening();
    }

}

