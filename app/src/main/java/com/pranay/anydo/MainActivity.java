package com.pranay.anydo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pranay.anydo.Activity.InsertNotesActivity;
import com.pranay.anydo.Adapter.NotesAdapter;
import com.pranay.anydo.ViewModel.NotesViewModel;



public class MainActivity extends AppCompatActivity {

    private NotesViewModel notesViewModel;

   private  RecyclerView notesRecycler;
   private NotesAdapter adapter;
   private FloatingActionButton addNewNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesRecycler = findViewById(R.id.notes_recyclerview);
        addNewNotes = findViewById(R.id.add_notes_fab);
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        addNewNotes.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InsertNotesActivity.class)));
        notesViewModel.getallNotes.observe(this, notes -> {

            notesRecycler.setLayoutManager(new GridLayoutManager(this,2));
            adapter = new NotesAdapter(MainActivity.this,notes);
            notesRecycler.setAdapter(adapter);

        });
    }
}