package com.mapp.androidcomponents.ContentProvider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MyDataFields.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract MyDataDAO myDataDAO();
    private static MyRoomDatabase INSTANCE;

    public static MyRoomDatabase getInstance(Context context){
        if (INSTANCE == null) {
            //Bad implementation because I am forcing to allow queries in the main Thread.
            //If the query is heavy it can cause an ARN.
            //For this example I will keep in this way because is a simple query
            INSTANCE = Room.databaseBuilder(context,   MyRoomDatabase.class, "datadb").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
