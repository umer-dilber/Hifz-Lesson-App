package com.example.hifz_lesson;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView txtDate, txtSabaq, txtSabqi, txtManzil;

    public MyViewHolder(View itemView) {
        super(itemView);
        txtDate = itemView.findViewById(R.id.date_view);
        txtSabaq = itemView.findViewById(R.id.sabaq_view);
        txtSabqi = itemView.findViewById(R.id.sabqi_view);
        txtManzil = itemView.findViewById(R.id.manzil_view);
    }
}
