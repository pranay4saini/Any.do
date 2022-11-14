package com.pranay.anydo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pranay.anydo.Activity.InsertNotesActivity;
import com.pranay.anydo.Adapter.NotesAdapter;
import com.pranay.anydo.Model.Notes;
import com.pranay.anydo.ViewModel.NotesViewModel;

import java.util.ArrayList;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private NotesViewModel notesViewModel;

    private RecyclerView notesRecycler;
    private NotesAdapter adapter;
    TextView nofilter, hightolow, lowtohigh;
    List<Notes> filterNameAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesRecycler = findViewById(R.id.notes_recyclerview);
        FloatingActionButton addNewNotes = findViewById(R.id.add_notes_fab);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        nofilter = findViewById(R.id.noFilter);
        hightolow = findViewById(R.id.highToLow);
        lowtohigh = findViewById(R.id.lowToHigh);

        nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

        nofilter.setOnClickListener(v -> {
            loadData(0);
            hightolow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_unselected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });
        hightolow.setOnClickListener(v -> {
            loadData(1);
            hightolow.setBackgroundResource(R.drawable.filter_selected_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_unselected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });
        lowtohigh.setOnClickListener(v -> {
            loadData(2);
            hightolow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_selected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });
        addNewNotes.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InsertNotesActivity.class)));

         notesViewModel.getallNotes.observe(this, notes -> {

             setAdapter(notes);
             filterNameAllList = notes;
         });


    }

    private void loadData(int i) {
        if (i == 0) {
            notesViewModel.getallNotes.observe(this, notes -> {

                setAdapter(notes);
                filterNameAllList = notes;
            });

        } else if (i == 1) {
            notesViewModel.hightolow.observe(this, notes -> {

                setAdapter(notes);
                filterNameAllList = notes;
            });
        } else if (i == 2) {
            notesViewModel.lowtohigh.observe(this, notes -> {

                setAdapter(notes);
                filterNameAllList = notes;
            });
        }
    }

    public void setAdapter(List<Notes> notes){
        notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this, notes);
        notesRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void notesFilter(String newText) {
        ArrayList<Notes> FilterNames = new ArrayList<>();

        for(Notes notes:this. filterNameAllList){
            if(notes.notesTitle.contains(newText) || notes.notesSubTitle.contains(newText)){
                FilterNames.add(notes);
            }

        }
        this.adapter.searchNotes(FilterNames);
    }
}