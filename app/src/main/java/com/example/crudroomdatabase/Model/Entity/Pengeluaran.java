package com.example.crudroomdatabase.Model.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_pengeluaran")
public class Pengeluaran implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id_pengeluaran;

    @ColumnInfo(name = "Keterangan")
    public String keterangan;

    @ColumnInfo(name="harga")
    public int harga;

    public Pengeluaran() {
    }

    protected Pengeluaran(Parcel in) {
        id_pengeluaran = in.readInt();
        keterangan = in.readString();
        harga = in.readInt();
    }

    public static final Creator<Pengeluaran> CREATOR = new Creator<Pengeluaran>() {
        @Override
        public Pengeluaran createFromParcel(Parcel in) {
            return new Pengeluaran(in);
        }

        @Override
        public Pengeluaran[] newArray(int size) {
            return new Pengeluaran[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id_pengeluaran);
        dest.writeString(this.keterangan);
        dest.writeInt(this.harga);
    }
}
