package com.example.meddata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView dataList;
    private Adapter adapter;
    public static Editable saved_notes;
    public static HashMap<String, Editable> profile_info= new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = findViewById(R.id.dataList);

        List<String> titles = new ArrayList<>();
        List<Integer> images = new ArrayList<>();
        titles.add("Save new visit");
        titles.add("Past visits");
        titles.add("Med profile");
        titles.add("Notes");
        titles.add("Bills");
        images.add(R.drawable.edit);
        images.add(R.drawable.list);
        images.add(R.drawable.profile);
        images.add(R.drawable.notes);
        images.add(R.drawable.bills);

        adapter = new Adapter(this, titles, images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);//set adapter to recyclerview
    }
}