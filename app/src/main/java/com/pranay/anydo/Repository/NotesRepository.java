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
        NotesDatabase.databaseWriteExecutor.execute(() -> notesDao.insertNotes(notes));

    }

   public void deleteNotes(int id){
       NotesDatabase.databaseWriteExecutor.execute(() -> notesDao.deleteNote(id));

    }
   public void updateNotes(Notes notes){
       NotesDatabase.databaseWriteExecutor.execute(() -> notesDao.updateNotes(notes));

    }

}
