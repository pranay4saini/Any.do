package com.pranay.anydo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pranay.anydo.Model.Notes;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database")
    LiveData<List<Notes>> getallNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Update
    void updateNotes(Notes notes);

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    void deleteNote(int id);

}
