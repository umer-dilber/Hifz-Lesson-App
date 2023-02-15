package com.example.hifz_lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class edit_details extends AppCompatActivity implements View.OnClickListener {
    TextView txtName;
    EditText date, sabaq, sabqi, manzil;
    int mYear, mMonth, mDay;
    Button btnSave;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_details);
        Intent intent = getIntent();
        name = intent.getStringExtra("my_data");
        txtName = findViewById(R.id.textview_Name);
        txtName.setText(name);

        date = findViewById(R.id.edittext_date);
        String dateStr = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date.setText(dateStr);
        date.setOnClickListener(this);

        sabaq = findViewById(R.id.edittext_sabaq);
        sabqi = findViewById(R.id.edittext_sabqi);
        manzil = findViewById(R.id.edittext_manzil);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
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

        if (view == btnSave){
            Users user = new Users(name);
            user.setDate(date.getText().toString());
            user.setSabaq(Integer.parseInt(sabaq.getText().toString()));
            user.setSabqi(Integer.parseInt(sabqi.getText().toString()));
            user.setManzil(Integer.parseInt(manzil.getText().toString()));
            DatabaseHelper db = new DatabaseHelper(this);
            if (!(db.insertUser(user))){
                Toast.makeText(this, "Values not updated", Toast.LENGTH_SHORT).show();
            }
            else{
                finish();
            }
        }
    }
}