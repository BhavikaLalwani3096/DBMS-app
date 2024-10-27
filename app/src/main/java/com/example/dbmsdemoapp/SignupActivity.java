package com.example.dbmsdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {
    userDBHelper userDB;
    EditText uname, pword, mail;
    Button loginBtn, signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signup_page);

        userDB = new userDBHelper(this);
        uname = findViewById(R.id.e1);
        mail = findViewById(R.id.e2);
        pword = findViewById(R.id.e3);
        signupBtn = findViewById(R.id.SignUpBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = uname.getText().toString();
                String email=mail.getText().toString();
                String password = pword.getText().toString();
                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Error! No text found.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(userDB.checkSignup(email)){
                    Toast.makeText(SignupActivity.this, "Account already exists. Please Login.", Toast.LENGTH_LONG).show();
                }
                if(userDB.isUsernameTaken(username)){
                    Toast.makeText(SignupActivity.this, "Username taken. Please enter new username.", Toast.LENGTH_LONG).show();
                }
                else{
                    userDB.insertUser(username, password, email, "user");
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
        loginBtn= findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
