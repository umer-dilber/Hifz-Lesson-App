package com.example.hifz_lesson;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    public FloatingActionButton fab;
    public RecyclerView recyclerView;
    public List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        DatabaseHelper database = new DatabaseHelper(this);
        names = database.getAllNames(); // Retrieve the names from the database?
        NamesAdapter adapter = new NamesAdapter(names);
        recyclerView.setAdapter(adapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            // create an instance of AlertDialog.Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // set the dialog title and message
            builder.setTitle("Enter Name");
            builder.setMessage("Please enter a name:");

            // create an EditText view to allow user input
            final EditText input = new EditText(this);
            builder.setView(input);

            // set the positive button action (i.e., OK button)
            builder.setPositiveButton("OK", (dialog, which) -> {
                String name = input.getText().toString();
                Users user = new Users(name);
                boolean flag =  database.insertUser(user);
                if (flag){
                    Toast.makeText(this, "User added", Toast.LENGTH_LONG).show();
                    names = database.getAllNames(); // Retrieve the names from the database?
                    NamesAdapter adapter1 = new NamesAdapter(names);
                    recyclerView.setAdapter(adapter1);
                }
                else{
                    Toast.makeText(this, "Not added", Toast.LENGTH_LONG).show();
                }
            });

            // set the negative button action (i.e., cancel button)
            builder.setNegativeButton("Cancel", (dialog, which) -> {
                dialog.cancel();
            });

            // create and show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();

        });
    }
}