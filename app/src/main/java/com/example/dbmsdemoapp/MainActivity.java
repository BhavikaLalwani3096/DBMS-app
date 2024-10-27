package com.example.dbmsdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    userDBHelper userDB;
    EditText uname, pword;
    Button loginBtn, signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        userDB = new userDBHelper(this);
        uname = findViewById(R.id.e1);
        pword = findViewById(R.id.e2);
        loginBtn = findViewById(R.id.loginBtn);
        userDB.insertUser("admintest", "admin123", "admin@example.com", "admin");
        userDB.insertUser("usertest", "user123", "user@example.com", "user");
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = uname.getText().toString();
                String password = pword.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Error! No text found.", Toast.LENGTH_LONG).show();
                    return;
                }
                String accessType = userDB.checkLogin(username, password);
                if (accessType == null) {
                    Toast.makeText(MainActivity.this, "Wrong username or password. Try again or sign up.", Toast.LENGTH_LONG).show();
                } else {
                    if (accessType.equals("admin")) {
                        Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                        startActivity(intent);
                    } else if (accessType.equals("user")) {
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        signupBtn= findViewById(R.id.pageBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}