package com.mapp.androidcomponents.ContentProvider;

import android.database.Cursor;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MyDataDAO {

    @Insert
    public void addData(MyDataFields dataFields);

    @Query("select * from data")
    public Cursor getMyData();

    @Delete
    public void delete(MyDataFields dataFields);

    @Update
    public void update(MyDataFields dataFields);

}
