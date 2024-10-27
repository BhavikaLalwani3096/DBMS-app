package com.example.dbmsdemoapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class userDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserDB.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";
    public static final String COL_ACCESS_TYPE = "access_type";

    public userDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " + // Ensure unique usernames
                COL_PASSWORD + " TEXT, " +
                COL_EMAIL + " TEXT UNIQUE, " + // Ensure unique emails
                COL_ACCESS_TYPE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String username, String password, String email, String accessType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_ACCESS_TYPE, accessType);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close(); // Close database after operation
        return result != -1;
    }

    @SuppressLint("Range")
    public String checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_ACCESS_TYPE + " FROM " + TABLE_NAME +
                " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});

        String accessType = null; // Default to null
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                accessType = cursor.getString(cursor.getColumnIndex(COL_ACCESS_TYPE));
            }
            cursor.close(); // Ensure cursor is closed
        }
        return accessType;
    }

    public boolean checkSignup(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + "=?", new String[]{email});

        boolean exists = false;
        if (cursor != null) {
            exists = cursor.moveToFirst();
            cursor.close();
        }

        return exists;
    }

    public boolean isUsernameTaken(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_USERNAME + "=?", new String[]{username});

        boolean exists = false;
        if (cursor != null) {
            exists = cursor.moveToFirst();
            cursor.close();
        }

        return exists;
    }

    public boolean deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_NAME, COL_USERNAME + "=?", new String[]{username});
        db.close();
        return deletedRows > 0;
    }

    public boolean changeAccessType(String username, String newAccessType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ACCESS_TYPE, newAccessType);

        int updatedRows = db.update(TABLE_NAME, contentValues, COL_USERNAME + "=?", new String[]{username});
        db.close();
        return updatedRows > 0;
    }

    public Cursor viewAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }
}
