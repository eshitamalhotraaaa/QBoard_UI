package com.example.qboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    TextView textView;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        textView = (TextView)findViewById(R.id.txt_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);

                // Toast.makeText(MainActivity.this, "Redirecting To Register", Toast.LENGTH_SHORT).show();
            }
        });

        login = (Button)findViewById(R.id.btnLogs);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Logs.class);
                startActivity(intent);

            }
        });
    }
}