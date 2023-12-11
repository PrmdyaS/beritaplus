package com.example.berita;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SearchHistory.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_HISTORY = "search_history";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SEARCH = "search_query";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_HISTORY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SEARCH + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }

    // Fungsi untuk menambahkan riwayat pencarian baru
    public void addSearchQuery(String searchQuery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SEARCH, searchQuery);
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    // Fungsi untuk mendapatkan semua riwayat pencarian
    public List<String> getAllSearchHistory() {
        List<String> historyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_HISTORY, null);
        if (cursor.moveToFirst()) {
            do {
                String history = cursor.getString(cursor.getColumnIndex(COLUMN_SEARCH));
                historyList.add(history);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return historyList;
    }
    public boolean deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_HISTORY, "ID = ?", new String[]{String.valueOf(id)}) > 0;
    }

    // Fungsi untuk menghapus semua riwayat pencarian
    public void clearSearchHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY, null, null);
        db.close();
    }

    public void addData(String title) {
    }
}