package com.example.dbmsdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etSection, etPrn, etEmail;
    RadioGroup rgGender;
    CheckBox cbTravelBlog, cbDesigning, cbCoding, cbSports;
    Button btnSubmit;
    classADBHelper db1;
    classBDBHelper db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration_form);

        etName = findViewById(R.id.et_name);
        etSection = findViewById(R.id.et_section);
        etPrn = findViewById(R.id.et_prn);
        etEmail = findViewById(R.id.et_email);
        rgGender = findViewById(R.id.rg_gender);
        cbTravelBlog = findViewById(R.id.cb_travel_blog);
        cbDesigning = findViewById(R.id.cb_designing);
        cbCoding = findViewById(R.id.cb_coding);
        cbSports = findViewById(R.id.cb_sports);
        btnSubmit = findViewById(R.id.btn_submit);

        db1 = new classADBHelper(this);
        db2 = new classBDBHelper(this);

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String section = etSection.getText().toString();
            String prn = etPrn.getText().toString();
            String email = etEmail.getText().toString();

            int selectedGenderId = rgGender.getCheckedRadioButtonId();
            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender.getText().toString();

            StringBuilder interests = new StringBuilder();
            if (cbTravelBlog.isChecked()) interests.append("Travelling and blog, ");
            if (cbDesigning.isChecked()) interests.append("Designing, ");
            if (cbCoding.isChecked()) interests.append("Coding, ");
            if (cbSports.isChecked()) interests.append("Sports");

            if(section.equals("A")){
                boolean isInserted = db1.insertStudent(name, prn, email, gender, interests.toString());
                if (isInserted){
                    Toast.makeText(RegisterActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, UserActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Data Insertion Failed", Toast.LENGTH_LONG).show();
                }
            }
            else if(section.equals("B")){
                boolean isInserted = db2.insertStudent(name, prn, email, gender, interests.toString());
                if (isInserted){
                    Toast.makeText(RegisterActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, UserActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Data Insertion Failed", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(RegisterActivity.this, "Section not found", Toast.LENGTH_LONG).show();
            }
        });
    }
}
