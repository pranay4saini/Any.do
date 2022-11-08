package com.pranay.anydo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name_title")
    public String notesTitle;

    @ColumnInfo(name = "name_subtitle")
    public String notesSubTitle;

    @ColumnInfo(name = "name")
    public String notes;

    @ColumnInfo(name = "name_priority")
    public String notesPriority;

    @ColumnInfo(name = "name_Date")
    public String notesDate;

}
