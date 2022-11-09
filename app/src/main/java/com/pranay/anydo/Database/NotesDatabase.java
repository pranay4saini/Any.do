package com.pranay.anydo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pranay.anydo.Dao.NotesDao;
import com.pranay.anydo.Model.Notes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Notes.class},version = 1)
public abstract class NotesDatabase extends RoomDatabase {


    public abstract NotesDao notesDao();
    private static  NotesDatabase INSTANCE;
    public static final int NUMBER_of_threads = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_of_threads);


    public static NotesDatabase getDatabaseInstance(Context context){


        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class,"Notes_Database")
                    .build();

        }

        return INSTANCE;

    }
}
