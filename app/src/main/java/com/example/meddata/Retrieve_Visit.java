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

    ////
    ListView listView;
    TextView textView;
    String[] listItem;

    ///

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

      //  ...........setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                displayVisit();
//            }
//        });

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

    public void displayVisit(String s){
        Intent intent = new Intent(this, Display_Visit.class);
        intent.putExtra("visitAddress", s);
        startActivity(intent);
    }
}

