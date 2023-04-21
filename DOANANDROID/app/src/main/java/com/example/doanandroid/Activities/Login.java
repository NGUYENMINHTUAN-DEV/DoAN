package com.example.doanandroid.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.R;

public class Login extends AppCompatActivity {
    EditText email , password;
    Button btnSubmit;
    TextView createAcc;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean e=false,p=false;
        setContentView(R.layout.login);

        email=findViewById(R.id.txtEmail);
        password=findViewById(R.id.txtPass);
        btnSubmit = findViewById(R.id.btnLogin);
        dbHelper = new DBHelper(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailCheck = email.getText().toString();
                String passCheck = password.getText().toString();
                Cursor  cursor = dbHelper.getData();
                if(cursor.getCount() == 0){
                    Toast.makeText(Login.this,"Không có mục nào tồn tại",Toast.LENGTH_LONG).show();
                }
                if (loginCheck(cursor,emailCheck,passCheck)) {

                    Intent intent = new Intent(Login.this, Menu.class);
                    intent.putExtra("email",emailCheck);
                    email.setText("");
                    password.setText("");
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setCancelable(true);
                    builder.setTitle("Thông tin đăng nhập sai ");
                    builder.setMessage("Thông tin đăng nhập sai");
                    builder.show();
                }
                dbHelper.close();

            }
        });
        
    }
    public static boolean loginCheck(Cursor cursor,String emailCheck,String passCheck) {
        while (cursor.moveToNext()){
            if (cursor.getString(0).equals(emailCheck)) {
                if (cursor.getString(2).equals(passCheck)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

}