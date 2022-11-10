package com.pranay.anydo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;



import com.pranay.anydo.Model.Notes;

import com.pranay.anydo.R;
import com.pranay.anydo.ViewModel.NotesViewModel;
import com.pranay.anydo.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {


   private ActivityInsertNotesBinding binding;
   String title,subtitle,notes;
   private NotesViewModel notesViewModel;

   public String priority = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  //getRoot()=Returns the outermost View in the layout file associated with the Binding

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.greenPriority.setOnClickListener(v -> {

            binding.greenPriority.setImageResource(R.drawable.baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority = "1";

        });
        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.baseline_done_24);
            binding.redPriority.setImageResource(0);
            priority = "2";
        });
        binding.redPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.baseline_done_24);
            priority = "3";
        });

        binding.fabSaveNotesButton.setOnClickListener(v -> {
            title = binding.titleEdittext.getText().toString();
            subtitle = binding.subtitleEdittext.getText().toString();
            notes = binding.notesDataEditText.getText().toString();

            CreateNotes(title,subtitle,notes);

        });



    }

    private void CreateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());
        Notes notesVar = new Notes();
        notesVar.notesTitle = title;
        notesVar.notesSubTitle = subtitle;
        notesVar.notes = notes;
        notesVar.notesPriority = priority;
        notesVar.notesDate = sequence.toString();
        notesViewModel.insert(notesVar);
        finish();

    }
}