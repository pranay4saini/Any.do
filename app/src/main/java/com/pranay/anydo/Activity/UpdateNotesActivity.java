package com.pranay.anydo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.pranay.anydo.Model.Notes;
import com.pranay.anydo.R;
import com.pranay.anydo.ViewModel.NotesViewModel;
import com.pranay.anydo.databinding.ActivityUpdateNotesBinding;

import java.util.Date;
import java.util.Objects;

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

        if(Objects.equals(updatePriority, "1")){
            binding.updateGreenPriority.setImageResource(R.drawable.baseline_done_24);
            
        } else if (Objects.equals(updatePriority, "2")) {
            binding.updateYellowPriority.setImageResource(R.drawable.baseline_done_24);
            
        } else if (Objects.equals(updatePriority, "3")) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.ic_delete){

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);

            View view = LayoutInflater.from(UpdateNotesActivity.this)
                    .inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottom_sheet_layout));
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();

            TextView yesDeleteTextView,noDeleteTextView;
            yesDeleteTextView = view.findViewById(R.id.delete_yes_textview);
            noDeleteTextView = view.findViewById(R.id.delete_no_textview);

            yesDeleteTextView.setOnClickListener(v ->{
                notesViewModel.delete(updateId);
                finish();
            });
            noDeleteTextView.setOnClickListener(v -> {
                bottomSheetDialog.dismiss();
            });

        }
        return true;
    }
}