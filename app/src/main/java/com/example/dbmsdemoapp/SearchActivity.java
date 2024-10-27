package com.example.dbmsdemoapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SearchActivity extends AppCompatActivity {
    EditText etNameFetch,sectionFetch;
    Button btnFetch;
    TextView tvDisplayInfo;
    classADBHelper db1;
    classBDBHelper db2;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_info);
        sectionFetch=findViewById(R.id.et_class_fetch);
        etNameFetch = findViewById(R.id.et_name_fetch);
        btnFetch = findViewById(R.id.btn_fetch);
        tvDisplayInfo = findViewById(R.id.tv_display_info);
        db1 = new classADBHelper(this);
        db2 = new classBDBHelper(this);
        btnFetch.setOnClickListener(v -> {
            String name = etNameFetch.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(SearchActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }
            String section = sectionFetch.getText().toString();
            if (section.isEmpty()) {
                Toast.makeText(SearchActivity.this, "Please enter your section", Toast.LENGTH_SHORT).show();
                return;
            }
            if(section.equals("A")){
                Cursor cursor = db1.getStudentData(name);  // Query for student data
                if (cursor.getCount() == 0) {
                    tvDisplayInfo.setText("No student found with the name: " + name);
                    return;
                }
                if (cursor.moveToFirst()) {
                    String prn = cursor.getString(cursor.getColumnIndexOrThrow("prn_no"));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    String gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));
                    String interests = cursor.getString(cursor.getColumnIndexOrThrow("interests"));

                    // Create a formatted string with all the information
                    String studentInfo = "Name: " + name + "\n"
                            + "PRN: " + prn + "\n"
                            + "Section: " + section + "\n"
                            + "Email: " + email + "\n"
                            + "Gender: " + gender + "\n"
                            + "Interests: " + interests;
                    tvDisplayInfo.setText(studentInfo);
                }
                cursor.close();
            }
            else if(section.equals("B")) {
                Cursor cursor = db2.getStudentData(name);  // Query for student data
                if (cursor.getCount() == 0) {
                    tvDisplayInfo.setText("No student found with the name: " + name);
                    return;
                }
                if (cursor.moveToFirst()) {
                    String prn = cursor.getString(cursor.getColumnIndexOrThrow("prn_no"));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    String gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));
                    String interests = cursor.getString(cursor.getColumnIndexOrThrow("interests"));

                    // Create a formatted string with all the information
                    String studentInfo = "Name: " + name + "\n"
                            + "PRN: " + prn + "\n"
                            + "Section: " + section + "\n"
                            + "Email: " + email + "\n"
                            + "Gender: " + gender + "\n"
                            + "Interests: " + interests;
                    tvDisplayInfo.setText(studentInfo);
                }
                cursor.close();
            }
        });
    }
}