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

    @Query("SELECT * FROM Notes_Database ORDER BY name_priority ASC ")
    LiveData<List<Notes>> lowtohighFilter();

    @Query("SELECT * FROM Notes_Database ORDER BY name_priority DESC")
    LiveData<List<Notes>> hightolowFilter();

    @Insert
    void insertNotes(Notes... notes);

    @Update
    void updateNotes(Notes notes);

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    void deleteNote(int id);

}
