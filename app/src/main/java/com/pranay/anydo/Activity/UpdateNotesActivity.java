package com.pranay.anydo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import com.pranay.anydo.Model.Notes;
import com.pranay.anydo.R;
import com.pranay.anydo.ViewModel.NotesViewModel;
import com.pranay.anydo.databinding.ActivityUpdateNotesBinding;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    private String priority;
    ActivityUpdateNotesBinding binding;
    private String updateTitle,updateSubtitle,updatePriority,updateNotes;
    private int updateId;
    private NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        updateId = getIntent().getIntExtra("id",0);
        updateTitle = getIntent().getStringExtra("title");
        updateSubtitle = getIntent().getStringExtra("subtitle");
        updatePriority = getIntent().getStringExtra("priority");
        updateNotes = getIntent().getStringExtra("notes");

        binding.updateTitleEdittext.setText(updateTitle);
        binding.updateSubtitleEdittext.setText(updateSubtitle);
        binding.updateNotesDataEditText.setText(updateNotes);

        if(updatePriority.equals("1")){
            binding.updateGreenPriority.setImageResource(R.drawable.baseline_done_24);
            
        } else if (updatePriority.equals("2")) {
            binding.updateYellowPriority.setImageResource(R.drawable.baseline_done_24);
            
        } else if (updatePriority.equals("3")) {
            binding.updateRedPriority.setImageResource(R.drawable.baseline_done_24);


        }


        binding.updateGreenPriority.setOnClickListener(v -> {

            binding.updateGreenPriority.setImageResource(R.drawable.baseline_done_24);
            binding.updateYellowPriority.setImageResource(0);
            binding.updateRedPriority.setImageResource(0);
            priority = "1";

        });
        binding.updateYellowPriority.setOnClickListener(v -> {
            binding.updateGreenPriority.setImageResource(0);
            binding.updateYellowPriority.setImageResource(R.drawable.baseline_done_24);
            binding.updateRedPriority.setImageResource(0);
            priority = "2";
        });
        binding.updateRedPriority.setOnClickListener(v -> {
            binding.updateGreenPriority.setImageResource(0);
            binding.updateYellowPriority.setImageResource(0);
            binding.updateRedPriority.setImageResource(R.drawable.baseline_done_24);
            priority = "3";
        });

        binding.fabUpdateNotesButton.setOnClickListener(v -> {

           String title = binding.updateTitleEdittext.getText().toString();
           String subtitle = binding.updateSubtitleEdittext.getText().toString();
           String notes = binding.updateNotesDataEditText.getText().toString();

            updateCreateNotes(title,subtitle,notes);

        });
    }

    private void updateCreateNotes(String title, String subtitle, String notes) {


        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes updatedNotes = new Notes();
        updatedNotes.id= updateId;
        updatedNotes.notesTitle = title;
        updatedNotes.notesSubTitle = subtitle;
        updatedNotes.notes = notes;
        updatedNotes.notesPriority = priority;
        updatedNotes.notesDate = sequence.toString();
        notesViewModel.update(updatedNotes);
        finish();
    }
}