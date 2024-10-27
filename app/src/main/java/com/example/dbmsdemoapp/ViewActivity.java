package com.example.dbmsdemoapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {
    Button userBtn, classABtn, classBBtn;
    TextView output;
    userDBHelper userDB;
    classADBHelper DB1;
    classBDBHelper DB2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.view_page);

        DB1 = new classADBHelper(this);
        DB2 = new classBDBHelper(this);
        userDB = new userDBHelper(this);

        output = findViewById(R.id.output);
        userBtn = findViewById(R.id.userBtn);
        classABtn = findViewById(R.id.ABtn);
        classBBtn = findViewById(R.id.BBtn);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayUserData();
            }
        });
        classABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayClassAData();
            }
        });
        classBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayClassBData();
            }
        });
    }

    private void displayUserData() {
        Cursor cursor = userDB.viewAllUsers();
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder data = new StringBuilder();
            do {
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(userDBHelper.COL_USERNAME));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(userDBHelper.COL_EMAIL));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(userDBHelper.COL_PASSWORD));
                @SuppressLint("Range") String accessType = cursor.getString(cursor.getColumnIndex(userDBHelper.COL_ACCESS_TYPE));

                data.append("Username: ").append(username).append("\n")
                        .append("Email: ").append(email).append("\n")
                        .append("Password: ").append(password).append("\n")
                        .append("Access Type: ").append(accessType).append("\n\n");
                Log.d("UserData", "Username: " + username + ", Email: " + email);
            } while (cursor.moveToNext());
            output.setText(data.toString());
        } else {
            output.setText("No data found");
            Log.d("UserData", "No data found");
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    private void displayClassAData() {
        Cursor cursor = DB1.viewAllStudents(); // Ensure you have this method in your classADBHelper
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder data = new StringBuilder();
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(classADBHelper.COL_NAME));
                @SuppressLint("Range") String prn = cursor.getString(cursor.getColumnIndex(classADBHelper.COL_PRN));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(classADBHelper.COL_EMAIL));
                @SuppressLint("Range") String gender = cursor.getString(cursor.getColumnIndex(classADBHelper.COL_GENDER));
                @SuppressLint("Range") String interests = cursor.getString(cursor.getColumnIndex(classADBHelper.COL_INTERESTS));

                data.append("Name: ").append(name).append("\n")
                        .append("PRN: ").append(prn).append("\n")
                        .append("Email: ").append(email).append("\n")
                        .append("Gender: ").append(gender).append("\n")
                        .append("Interests: ").append(interests).append("\n\n");
                Log.d("StudentData", "Name: " + name + "PRN: "+prn + ", Email: " + email + ", Gender: " + gender + ", Interests: " + interests);
            } while (cursor.moveToNext());
            output.setText(data.toString());
        } else {
            output.setText("No data found");
            Log.d("StudentData", "No data found");
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    private void displayClassBData() {
        Cursor cursor = DB2.viewAllStudents(); // Ensure you have this method in your classBDBHelper
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder data = new StringBuilder();
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(classBDBHelper.COL_NAME));
                @SuppressLint("Range") String prn = cursor.getString(cursor.getColumnIndex(classBDBHelper.COL_PRN));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(classBDBHelper.COL_EMAIL));
                @SuppressLint("Range") String gender = cursor.getString(cursor.getColumnIndex(classBDBHelper.COL_GENDER));
                @SuppressLint("Range") String interests = cursor.getString(cursor.getColumnIndex(classBDBHelper.COL_INTERESTS));

                data.append("Name: ").append(name).append("\n")
                        .append("PRN: ").append(prn).append("\n")
                        .append("Email: ").append(email).append("\n")
                        .append("Gender: ").append(gender).append("\n")
                        .append("Interests: ").append(interests).append("\n\n");

                Log.d("StudentData", "Name: " + name + "PRN: "+prn + ", Email: " + email + ", Gender: " + gender + ", Interests: " + interests);
            } while (cursor.moveToNext());
            output.setText(data.toString());
        } else {
            output.setText("No data found");
            Log.d("StudentData", "No data found");
        }
        if (cursor != null) {
            cursor.close();
        }
    }
}
