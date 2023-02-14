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
        db.execSQL("CREATE TABLE Users (Name String UNIQUE, Date String, Sabqi INTEGER, Sabaq INTEGER, Manzil INTEGER)");
        db.execSQL("CREATE UNIQUE INDEX idx_users_date_name ON users(Date, Name)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //handel database upgrades
    }

    public boolean insertUser(Users user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", user.getName());
        values.put("Date", user.getDate());
        values.put("Sabqi", user.getSabqi());
        values.put("Sabaq", user.getSabaq());
        values.put("Manzil", user.getManzil());
        long flag = db.insert("Users", null, values);
        db.close();
        if (flag == -1) {
            return false; // insertion failed
        } else {
            return true; // insertion successful
        }
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
        String selectQuery = "SELECT DISTINCT Name FROM Users";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() > 0) {
            int nameIndex = cursor.getColumnIndex("Name");
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

    public boolean deleteUserByName(String name) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "Name=?";
        String[] whereArgs = { name };
        int deletedRows = db.delete("Users", whereClause, whereArgs);
        db.close();
        // deletion failed
        return deletedRows > 0; // deletion successful
    }

    public List<Users> getAllEntriesByName(String name) {
        List<Users> entriesList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Users WHERE Name=?";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{name});
        if (cursor != null && cursor.getCount() > 0) {
            int nameIndex = cursor.getColumnIndex("Name");
            int dateIndex = cursor.getColumnIndex("Date");
            int sabqiIndex = cursor.getColumnIndex("Sabqi");
            int sabaqIndex = cursor.getColumnIndex("Sabaq");
            int manzilIndex = cursor.getColumnIndex("Manzil");
            while (cursor.moveToNext()) {
                String entryName = cursor.getString(nameIndex);
                String date = cursor.getString(dateIndex);
                int sabqi = cursor.getInt(sabqiIndex);
                int sabaq = cursor.getInt(sabaqIndex);
                int manzil = cursor.getInt(manzilIndex);
                Users entry = new Users(entryName);
                entry.setDate(date);
                entry.setSabaq(sabaq);
                entry.setSabqi(sabqi);
                entry.setManzil(manzil);
                entriesList.add(entry);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return entriesList;
    }


}
