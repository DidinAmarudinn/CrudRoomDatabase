package com.example.crudroomdatabase.View.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.crudroomdatabase.Adapter.PengeluaranAdapter;
import com.example.crudroomdatabase.Database.FunctionHelper;
import com.example.crudroomdatabase.Model.Entity.Pengeluaran;
import com.example.crudroomdatabase.R;
import com.example.crudroomdatabase.View.add.Add_Data;
import com.example.crudroomdatabase.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PengeluaranAdapter.PengeluaranAdapterCallback{
    private ActivityMainBinding binding;
    private PengeluaranAdapter adapter;
    private MainViewModel mainViewModel;
    private List<Pengeluaran> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();
        observeData();

        initAction();

    }



    private void initAction() {
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Data.startActivity(MainActivity.this,false,null);
            }
        });
        binding.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.deleteAllData();
                binding.tvTotal.setText("0");
            }
        });
    }

    private void initAdapter() {
        adapter=new PengeluaranAdapter(this,list,this);
        binding.rvPenegluaran.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPenegluaran.setHasFixedSize(true);
        binding.rvPenegluaran.setItemAnimator(new DefaultItemAnimator());
        binding.rvPenegluaran.setAdapter(adapter);
    }
    private void observeData() {
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getPengeluaran().observe(this, new Observer<List<Pengeluaran>>() {
            @Override
            public void onChanged(List<Pengeluaran> pengeluarans) {
                if (pengeluarans.isEmpty()){
                    binding.btnHapus.setVisibility(View.GONE);
                }else {
                    binding.btnHapus.setVisibility(View.VISIBLE);
                }
                adapter.addData(pengeluarans);
            }
        });
        mainViewModel.getTotalPrice().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer==null){
                    int totalPrice=0;
                    String initPrice= FunctionHelper.rupiahFormater(totalPrice);
                    binding.tvTotal.setText(initPrice);
                }else {
                    int totalPrice=integer;
                    String initPrice=FunctionHelper.rupiahFormater(totalPrice);
                    binding.tvTotal.setText(initPrice);
                }
            }
        });
    }

    @Override
    public void onEdit(Pengeluaran pengeluaran) {
        Add_Data.startActivity(this,true,pengeluaran);
    }

    @Override
    public void onDelete(Pengeluaran pengeluaran) {
        int id_pengeluaran=pengeluaran.id_pengeluaran;
        mainViewModel.deleteSingleData(id_pengeluaran);
    }
}
