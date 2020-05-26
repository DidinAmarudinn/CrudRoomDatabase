package com.example.crudroomdatabase.View.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.crudroomdatabase.Database.DatabaseClient;
import com.example.crudroomdatabase.Database.PengeluaranDao;
import com.example.crudroomdatabase.Model.Entity.Pengeluaran;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Pengeluaran>> listLiveData;
    private PengeluaranDao pengeluaranDao;
    private LiveData<Integer> totalPrice;

    public MainViewModel(@NonNull Application application) {
        super(application);
        pengeluaranDao= DatabaseClient.getInstance(application)
                .getAppDatabase().pengeluaranDao();
        listLiveData=pengeluaranDao.getAllData();
        totalPrice=pengeluaranDao.getTotalHarga();

    }

    public LiveData<List<Pengeluaran>> getPengeluaran(){
        return  listLiveData;
    }

    public LiveData<Integer> getTotalPrice(){
        return totalPrice;
    }
    public void deleteSingleData(final int id_pengeluaran){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                pengeluaranDao.deleteSingleData(id_pengeluaran);
            }
        })      .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public  void deleteAllData(){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                pengeluaranDao.deleteAllDAta();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }
}
