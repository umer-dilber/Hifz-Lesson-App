package com.example.hifz_lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class edit_details extends AppCompatActivity implements View.OnClickListener {
    TextView txtName;
    EditText date;
    int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_details);
        Intent intent = getIntent();
        String name = intent.getStringExtra("my_data");
        txtName = findViewById(R.id.textview_Name);
        txtName.setText(name);

        date = findViewById(R.id.edittext_date);
        String dateStr = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date.setText(dateStr);
        date.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == date) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(edit_details.this,
                    (view1, year, month, day) -> {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, day);
                        if (selectedDate.after(c)) {
                            Toast.makeText(edit_details.this, "Selected date can't be greater than today's date.", Toast.LENGTH_SHORT).show();
                        } else {
//                            String Locale = "";
                            date.setText(String.format("%02d-%02d-%04d", day, month + 1, year));
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}