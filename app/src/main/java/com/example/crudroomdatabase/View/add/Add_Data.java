package com.example.crudroomdatabase.View.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudroomdatabase.Model.Entity.Pengeluaran;
import com.example.crudroomdatabase.R;
import com.example.crudroomdatabase.databinding.ActivityAddDataBinding;

public class Add_Data extends AppCompatActivity {
    private static String KEY_IS_EDIT="key_is_edit";
    private static String KEY_DATA="key_data";

    public static void startActivity(Context context, boolean isEdit, Pengeluaran pengeluaran){
        Intent intent=new Intent(new Intent(context,Add_Data.class));
        intent.putExtra(KEY_IS_EDIT,isEdit);
        intent.putExtra(KEY_DATA,pengeluaran);
        context.startActivity(intent);
    }
    private ActivityAddDataBinding dataBinding;
    private AddDataViewModel addDataViewModel;

    private boolean is_edit=false;
    private int mid_pengeluaran=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding=ActivityAddDataBinding.inflate(getLayoutInflater());
        setContentView(dataBinding.getRoot());
        addDataViewModel = ViewModelProviders.of(this).get(AddDataViewModel.class);
        loadData();
        initAction();


    }

    private void loadData() {
        is_edit=getIntent().getBooleanExtra(KEY_DATA,false);
        if (is_edit){
            Pengeluaran pengeluaran=getIntent().getParcelableExtra(KEY_DATA);
            if (pengeluaran!=null){
                mid_pengeluaran=pengeluaran.id_pengeluaran;
                String note=pengeluaran.keterangan;
                int price=pengeluaran.harga;
                dataBinding.etKeterangan.setText(note);
                dataBinding.etHarga.setText(price);
            }
        }
    }

    private void initAction() {
        dataBinding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note=dataBinding.etKeterangan.getText().toString();
                String price=dataBinding.etHarga.getText().toString();

                if (note.isEmpty() || price.isEmpty()){
                    Toast.makeText(Add_Data.this,"Mohon isi semua field yang kosong",Toast.LENGTH_LONG).show();
                }else {
                    if (is_edit){
                        addDataViewModel.updatePengeluaran(mid_pengeluaran,note, Integer.parseInt(price));
                    }else {
                        addDataViewModel.addPengeluaran(note, Integer.parseInt(price));
                    }
                    finish();
                }
            }
        });
    }
}
