package com.pranay.anydo.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pranay.anydo.Activity.UpdateNotesActivity;
import com.pranay.anydo.MainActivity;
import com.pranay.anydo.Model.Notes;
import com.pranay.anydo.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {
    private final MainActivity mainActivity;
    private List<Notes> notes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {

        this.mainActivity = mainActivity;
        this.notes = notes;
        List<Notes> completeNotes = new ArrayList<>(notes);

    }
    public void searchNotes(List<Notes> filterredNotes){
        this.notes = filterredNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesAdapter.notesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.recycler_view_items_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.notesViewholder holder, int position) {


        Notes notePositionVar = notes.get(position);
        if ("1".equals(notePositionVar.notesPriority)) {
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        } else if ("2".equals(notePositionVar.notesPriority)) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if ("3".equals(notePositionVar.notesPriority)) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }
        holder.title.setText(notePositionVar.notesTitle);
        holder.subtitle.setText(notePositionVar.notesSubTitle);
        holder.notesDate.setText(notePositionVar.notesDate);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);

            intent.putExtra("id",notePositionVar.id);
            intent.putExtra("title",notePositionVar.notesTitle);
            intent.putExtra("subtitle",notePositionVar.notesSubTitle);
            intent.putExtra("priority",notePositionVar.notesPriority);
            intent.putExtra("notes",notePositionVar.notes);

            mainActivity.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class notesViewholder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView subtitle;
        private final TextView notesDate;
         View notesPriority;

        public notesViewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notessubTitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);

        }
    }
}
