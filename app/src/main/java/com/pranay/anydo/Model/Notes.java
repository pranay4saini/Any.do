package com.pranay.anydo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name_title")
    String notesTitle;

    @ColumnInfo(name = "name_subtitle")
    String notesSubTitle;

    @ColumnInfo(name = "name")
    String notes;

    @ColumnInfo(name = "name_priority")
    String notesPriority;

    @ColumnInfo(name = "name_Date")
    String notesDate;

}
