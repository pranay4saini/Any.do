package com.pranay.anydo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

    private RecyclerView notesRecycler;
    private NotesAdapter adapter;
    private FloatingActionButton addNewNotes;
    TextView nofilter, hightolow, lowtohigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesRecycler = findViewById(R.id.notes_recyclerview);
        addNewNotes = findViewById(R.id.add_notes_fab);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        nofilter = findViewById(R.id.noFilter);
        hightolow = findViewById(R.id.highToLow);
        lowtohigh = findViewById(R.id.lowToHigh);

        nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

        nofilter.setOnClickListener(v -> {
            hightolow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_unselected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });
        hightolow.setOnClickListener(v -> {
            hightolow.setBackgroundResource(R.drawable.filter_selected_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_unselected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });
        lowtohigh.setOnClickListener(v -> {
            hightolow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_selected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });
        addNewNotes.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InsertNotesActivity.class)));
        notesViewModel.getallNotes.observe(this, notes -> {

            notesRecycler.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new NotesAdapter(MainActivity.this, notes);
            notesRecycler.setAdapter(adapter);

        });
    }
}