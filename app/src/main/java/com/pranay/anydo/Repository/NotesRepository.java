package com.pranay.anydo.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.pranay.anydo.Dao.NotesDao;
import com.pranay.anydo.Database.NotesDatabase;
import com.pranay.anydo.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;

    public NotesRepository(Application application){
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getallNotes = notesDao.getallNotes();
    }

   public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

   public void deleteNotes(int id){
        notesDao.deleteNote(id);
    }
   public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }

}
