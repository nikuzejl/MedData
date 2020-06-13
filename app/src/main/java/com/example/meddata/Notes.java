package com.example.meddata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Notes extends AppCompatActivity {
    Button save_notes;
    EditText text_box;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        text_box = findViewById(R.id.text_box);
        text_box.setText(MainActivity.saved_notes);
        save_notes = findViewById(R.id.save_notes);

        text_box.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            text_box.setCursorVisible(true);
                                        }
        });

        save_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.saved_notes = text_box.getText();
                Toast.makeText(Notes.this, "Your changes have been saved", Toast.LENGTH_SHORT).show();
                text_box.setCursorVisible(false);
            }
        });
    }
}