package com.example.meddata;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class Retrieve_Visit extends AppCompatActivity {
    private FirebaseDatabase db;
      private FirebaseFirestore dbFS = FirebaseFirestore.getInstance();
    private DatabaseReference dbRef;
    private VisitAdapter visitAdapter;
    private New_VisitAdapter new_visitAdapter;
    private ChildEventListener mChildEventListener;
       private CollectionReference dbFScollections = dbFS.collection("forms");

    private ListView allVisits;

    ////
//    ListView listView;
//    TextView textView;
//    String[] listItem;

    ///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_visit);

        setUpRecyclerView();
        ////keeeeep this
  /*
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

        allVisits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //displayVisit(visit_address);


               Toast.makeText(getApplicationContext(),"Click ListItem Number " + position, Toast.LENGTH_LONG).show();
            }
        });

  */

////////
//        listView=(ListView)findViewById(R.id.all_visits);
//        textView=(TextView)findViewById(R.id.textView);
//        listItem = getResources().getStringArray(R.array.array_technology);
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                // TODO Auto-generated method stub
//                String value=adapter.getItem(position);
//                Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        //////
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
                Toast.makeText(Retrieve_Visit.this,
                        "Position: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();
            }
        });
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

//    public void displayVisit(String s){
//        Intent intent = new Intent(this, Display_Visit.class);
//        intent.putExtra("visitAddress", s);
//        startActivity(intent);
//    }
}

