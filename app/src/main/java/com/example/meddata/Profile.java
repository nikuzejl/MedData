package com.example.meddata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Profile extends AppCompatActivity {
    EditText glyc, bp, chol, herDis, alrg;
    Button save_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        glyc = findViewById(R.id.glyc);
        save_profile = findViewById(R.id.save_profile);

        glyc.setText(MainActivity.profile_info.get("glycemia"));

        bp = findViewById(R.id.bp);
        bp.setText(MainActivity.profile_info.get("blood_pressure"));

        chol= findViewById(R.id.chol);
        chol.setText(MainActivity.profile_info.get("cholesterol_level"));

        herDis = findViewById(R.id.herDis);
        herDis.setText(MainActivity.profile_info.get("hereditary_disease"));

        alrg = findViewById(R.id.alrg);;
        alrg.setText(MainActivity.profile_info.get("allergies"));

        glyc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    glyc.setCursorVisible(true);
                }
        });
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.setCursorVisible(true);
            }
        });

        chol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chol.setCursorVisible(true);
            }
        });
        herDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                herDis.setCursorVisible(true);
            }
        });
        alrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alrg.setCursorVisible(true);
            }
        });

        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.profile_info.put("glycemia", glyc.getText());
                glyc.setCursorVisible(false);

                MainActivity.profile_info.put("blood_pressure", bp.getText());
                bp.setCursorVisible(false);

                MainActivity.profile_info.put("cholesterol_level", chol.getText());
                chol.setCursorVisible(false);

                MainActivity.profile_info.put("hereditary_disease", herDis.getText());
                herDis.setCursorVisible(false);

                MainActivity.profile_info.put("allergies", alrg.getText());
                alrg.setCursorVisible(false);

                Toast.makeText(Profile.this, "Your changes have been saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}