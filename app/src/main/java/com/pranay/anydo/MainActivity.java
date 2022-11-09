package com.pranay.anydo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pranay.anydo.Activity.InsertNotesActivity;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addNewNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNewNotes = findViewById(R.id.add_notes_fab);

        addNewNotes.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });
    }
}