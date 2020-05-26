package com.example.crudroomdatabase.Database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private static DatabaseClient mInstance;
    private AppDatabase appDatabase;


    public DatabaseClient(Context context) {
        appDatabase= Room.databaseBuilder(context,AppDatabase.class,"pengeluaran_db")
                .fallbackToDestructiveMigration()
                .build();

    }
    public static synchronized DatabaseClient getInstance(Context context){
        if(mInstance==null){
            mInstance=new DatabaseClient(context);
        }
        return mInstance;
    }

    public static DatabaseClient getInstance(){
        if (mInstance==null){
            return mInstance;
        }
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");
    }
    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
