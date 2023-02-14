package com.example.hifz_lesson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewDataAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Users> users;

    public ViewDataAdapter(List<Users> a_users){
        this.users = a_users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Users user = users.get(position);
        holder.txtDate.setText(user.getDate());
        holder.txtSabaq.setText((user.getSabaq()).toString());
        holder.txtSabqi.setText((user.getSabqi()).toString());
        holder.txtManzil.setText((user.getManzil()).toString());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}