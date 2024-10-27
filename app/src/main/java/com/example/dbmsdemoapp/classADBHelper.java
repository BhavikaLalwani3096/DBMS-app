package com.example.dbmsdemoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class classADBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ClassADB.db";
    public static final String TABLE_NAME = "TYCSEA";
    public static final String COL_NAME = "name";
    public static final String COL_PRN = "prn_no";
    public static final String COL_EMAIL = "email";
    public static final String COL_GENDER = "gender";
    public static final String COL_INTERESTS = "interests";

    public classADBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Fixed the CREATE TABLE statement with proper spacing
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_PRN + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_GENDER + " TEXT, " + // Added a comma here
                COL_INTERESTS + " TEXT)"); // Added a space before COL_INTERESTS
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertStudent(String name, String prn, String email, String gender, String interests) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_PRN, prn);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_GENDER, gender);
        contentValues.put(COL_INTERESTS, interests);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // Return true if insertion was successful
    }

    public Cursor getStudentData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = ?";
        return db.rawQuery(query, new String[]{name});
    }

    public Cursor viewAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }
}
