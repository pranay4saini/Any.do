package com.pranay.anydo.ViewModel;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pranay.anydo.Model.Notes;
import com.pranay.anydo.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getallNotes;
    public NotesViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);

        getallNotes = repository.getallNotes;
    }
   public void insert(Notes notes){
        repository.insertNotes(notes);
    }
   public void delete(int id){
        repository.deleteNotes(id);
    }
   public void update(Notes notes){
        repository.updateNotes(notes);
    }
}
