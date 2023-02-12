package com.example.hifz_lesson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Hifz-Lesson";
    public static final int DATABASE_VERSION = 1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users (Name String UNIQUE, Sabqi INTEGER, Sabaq INTEGER, Manzil INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //handel database upgrades
    }

    public boolean insertUser(Users user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("Sabqi", user.getSabqi());
        values.put("Sabaq", user.getSabaq());
        values.put("Manzil", user.getManzil());
        db.insert("users", null, values);
        long flag = db.insert("SalahRecord", null, values);
        db.close();
        return (flag != -1);
    }

    public void updateValues(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Sabqi", user.getSabqi());
        values.put("Sabaq", user.getSabaq());
        values.put("Manzil", user.getManzil());
        db.update("Users", values, "Name=?", new String[] { user.getName() });
        db.close();
    }

    public List<String> getAllNames() {
        List<String> namesList = new ArrayList<>();
        String selectQuery = "SELECT name FROM users";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() > 0) {
            int nameIndex = cursor.getColumnIndex("name");
            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                namesList.add(name);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return namesList;
    }


}
