package com.pranay.anydo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;


import com.pranay.anydo.Model.Notes;

import com.pranay.anydo.ViewModel.NotesViewModel;
import com.pranay.anydo.databinding.ActivityInsertNotesBinding;

public class InsertNotesActivity extends AppCompatActivity {


   private ActivityInsertNotesBinding binding;
   String title,subtitle,notes;
   NotesViewModel notesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  //getRoot()=Returns the outermost View in the layout file associated with the Binding

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.fabSaveNotesButton.setOnClickListener(v -> {
            title = binding.titleEdittext.getText().toString();
            subtitle = binding.subtitleEdittext.getText().toString();
            notes = binding.notesDataEditText.getText().toString();

            CreaeteNotes(title,subtitle,notes);

        });



    }

    private void CreaeteNotes(String title, String subtitle, String notes) {
        Notes notesVar = new Notes();
        notesVar.notesTitle = title;
        notesVar.notesSubTitle = subtitle;
        notesVar.notes = notes;
        notesViewModel.insert(notesVar);
        finish();

    }
}