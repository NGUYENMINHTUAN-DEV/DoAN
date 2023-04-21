package com.example.doanandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.R;

public class Register extends AppCompatActivity {
    EditText email , pass,password;
    TextView login;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        email=findViewById(R.id.txtEmail);
        pass=findViewById(R.id.txtpass);
        password=findViewById(R.id.txtPassword);
        Button signUpAcc = findViewById(R.id.btnDangKi);
        dbHelper = new DBHelper(this);
        signUpAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();
                String password1 = password.getText().toString();
                boolean b =dbHelper.insetUserData(email1,pass1,password1);
                if (b){
                    Toast.makeText(Register.this,"Đã chèn dữ liệu",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Register.this, Login.class);
                    startActivity(i);
                }else {
                    Toast.makeText(Register.this,"Không thể chèn dữ liệu",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}