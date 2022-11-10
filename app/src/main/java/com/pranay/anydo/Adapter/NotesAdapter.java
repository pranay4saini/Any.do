package com.pranay.anydo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pranay.anydo.MainActivity;
import com.pranay.anydo.Model.Notes;
import com.pranay.anydo.R;

import java.util.List;
import java.util.Objects;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {
    private MainActivity mainActivity;
    private List<Notes> notes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {

        this.mainActivity = mainActivity;
        this.notes = notes;

    }

    @NonNull
    @Override
    public NotesAdapter.notesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.recycler_view_items_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.notesViewholder holder, int position) {


        Notes notePositionVar = notes.get(position);
        if (Objects.equals(notePositionVar.notesPriority, "1")) {
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        } else if (Objects.equals(notePositionVar.notesPriority, "2")) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (Objects.equals(notePositionVar.notesPriority, "3")) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }
        holder.title.setText(notePositionVar.notesTitle);
        holder.subtitle.setText(notePositionVar.notesSubTitle);
        holder.notesDate.setText(notePositionVar.notesDate);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class notesViewholder extends RecyclerView.ViewHolder {
        private TextView title, subtitle, notesDate;
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
