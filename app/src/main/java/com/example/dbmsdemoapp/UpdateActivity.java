package com.example.dbmsdemoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    EditText uname,access;
    Button deleteBtn, accessBtn;
    userDBHelper userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.update_page);
        userDB=new userDBHelper(this);
        uname=findViewById(R.id.uname);
        access=findViewById(R.id.access);
        deleteBtn=findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=uname.getText().toString();
                boolean isDeleted=userDB.deleteUser(username);
                if(isDeleted){
                    Toast.makeText(UpdateActivity.this, "User deleted.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(UpdateActivity.this, "User deletion failed.", Toast.LENGTH_LONG).show();
                }
            }
        });
        accessBtn=findViewById(R.id.setBtn);
        accessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=uname.getText().toString();
                String setAccess=access.getText().toString();
                boolean isSet =userDB.changeAccessType(username,setAccess);
                if(isSet){
                    Toast.makeText(UpdateActivity.this, "Access changed successfully.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(UpdateActivity.this, "Access change failed.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
