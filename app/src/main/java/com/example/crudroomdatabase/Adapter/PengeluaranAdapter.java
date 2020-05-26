package com.example.crudroomdatabase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudroomdatabase.Database.FunctionHelper;
import com.example.crudroomdatabase.Model.Entity.Pengeluaran;
import com.example.crudroomdatabase.databinding.ItemPengeluaranBinding;

import java.util.List;

public class PengeluaranAdapter extends RecyclerView.Adapter<PengeluaranAdapter.ViewHolder> {
    private static final String TAG = PengeluaranAdapter.class.getSimpleName();
    private Context context;
    private List<Pengeluaran> list;
    private PengeluaranAdapterCallback pengeluaranAdapterCallback;
    private ItemPengeluaranBinding binding;

    public PengeluaranAdapter(Context context, List<Pengeluaran> list, PengeluaranAdapterCallback pengeluaranAdapterCallback) {
        this.context = context;
        this.list = list;
        this.pengeluaranAdapterCallback = pengeluaranAdapterCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=ItemPengeluaranBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pengeluaran item=list.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear(){
        int size=this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0,size);
    }
    public void addData(List<Pengeluaran> pengeluarans){
        this.list=pengeluarans;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemPengeluaranBinding itemView) {
            super(itemView.getRoot());
            itemView.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Pengeluaran pengeluaran=list.get(getAdapterPosition());
                    pengeluaranAdapterCallback.onEdit(pengeluaran);
                    return true;
                }
            });
            binding.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pengeluaran pengeluaran=list.get(getAdapterPosition());
                    pengeluaranAdapterCallback.onDelete(pengeluaran);

                }
            });

        }
        void bindData(Pengeluaran item){
            int price=item.harga;
            String initPrice= FunctionHelper.rupiahFormater(price);
            binding.tvPrice.setText(initPrice);

            String note=item.keterangan;
            binding.tvNote.setText(note);
        }
    }

    public interface PengeluaranAdapterCallback{
        void onEdit(Pengeluaran pengeluaran);
        void onDelete(Pengeluaran pengeluaran);
    }
}
