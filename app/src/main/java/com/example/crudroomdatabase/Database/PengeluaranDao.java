package com.example.crudroomdatabase.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.crudroomdatabase.Model.Entity.Pengeluaran;

import java.util.List;

@Dao
public interface PengeluaranDao {
    @Query("SELECT * FROM tbl_pengeluaran")
    LiveData<List<Pengeluaran>> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(Pengeluaran... pengeluarans);

    @Query("DELETE FROM tbl_pengeluaran WHERE id_pengeluaran= :id_pengeluaran")
    void deleteSingleData(int id_pengeluaran);

    @Query("SELECT SUM(harga) FROM tbl_pengeluaran")
    LiveData<Integer> getTotalHarga();

    @Query("Update tbl_pengeluaran SET keterangan= :keterangan, harga= :harga WHERE id_pengeluaran= :id_pengeluaran")
    void updateData(String keterangan, int harga, int id_pengeluaran);

    @Query("DELETE FROM tbl_pengeluaran")
    void deleteAllDAta();
}
