package com.a.b.c.d;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.ResultSet;

public class Login extends AppCompatActivity {

    private EditText edUserName,edPassword;
    private Button button_login;
    String username,password;
    Connection connection;
    ResultSet resultSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserName = findViewById(R.id.editText_userName);
        edPassword = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.button_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edUserName.getText().toString();
                password = edPassword.getText().toString();
                if(username.equals("admin") && password.equals("admin"))
                {
                    Intent i=new Intent(Login.this, MainActivity.class);
                    startActivity(i);


                }


            }
        });

    }

    }

