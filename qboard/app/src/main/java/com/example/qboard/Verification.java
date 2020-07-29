package com.example.qboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Verification extends AppCompatActivity {

    Button Next;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        //To go on the next Activity of entering OTP code

        textView = (TextView)findViewById(R.id.txt_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Verification.this, Login.class);
                startActivity(intent);

                // Toast.makeText(MainActivity.this, "Redirecting To Register", Toast.LENGTH_SHORT).show();
            }
        });

        Next = (Button)findViewById(R.id.btnNext);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Verification.this, OTPVerification.class);
                startActivity(intent);

            }
        });

    }
}