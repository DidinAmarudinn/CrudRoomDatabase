package com.example.crudroomdatabase.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.crudroomdatabase.Model.Entity.Pengeluaran;

@Database(entities = {Pengeluaran.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PengeluaranDao pengeluaranDao();
}
