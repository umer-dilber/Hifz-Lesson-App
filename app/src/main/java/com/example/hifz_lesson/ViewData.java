package com.example.hifz_lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ViewData extends AppCompatActivity {
    RecyclerView resView;
    TextView txtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        resView = findViewById(R.id.recyclerview_user);
        txtName = findViewById(R.id.textView);
        Intent intent = getIntent();
        String name = intent.getStringExtra("my_data");
        txtName.setText(name);
        DatabaseHelper db = new DatabaseHelper(this);
        List<Users> items = db.getAllEntriesByName(name);
        items = items.subList(1, items.size());
        ViewDataAdapter adapt = new ViewDataAdapter(items);
        resView.setAdapter(adapt);
    }
}