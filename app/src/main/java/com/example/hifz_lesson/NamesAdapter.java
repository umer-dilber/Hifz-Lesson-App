package com.example.hifz_lesson;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.ViewHolder> {
    private List<String> names;

    public NamesAdapter(List<String> names) {
        this.names = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = names.get(position);
        holder.nameTextView.setText(name);
        holder.btnDelete.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(holder.btnDelete.getContext());
            if (db.deleteUserByName((holder.nameTextView.getText()).toString())){
                Toast.makeText(holder.btnDelete.getContext(), "Row deleted", Toast.LENGTH_SHORT).show();
                this.names = db.getAllNames(); // Retrieve the names from the database?
                notifyDataSetChanged();
            }
        });

        holder.btnView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ViewData.class);
            intent.putExtra("my_data", (holder.nameTextView.getText()).toString());
            v.getContext().startActivity(intent);
        });

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), edit_details.class);
            intent.putExtra("my_data", (holder.nameTextView.getText()).toString());
            v.getContext().startActivity(intent);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        Button btnView, btnEdit, btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textview);
            btnView = itemView.findViewById(R.id.btnView);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

}

