package com.example.qboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TextView textView;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //The Code for clickable Register Text

        textView = (TextView)findViewById(R.id.txt_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Verification.class);
                startActivity(intent);

               // Toast.makeText(MainActivity.this, "Redirecting To Register", Toast.LENGTH_SHORT).show();
            }
        });

        //The Code To Navigate To Login Page

        login = (Button)findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        });

        // The Code for the ViewPagers

        models = new ArrayList<>();
        models.add(new Model(R.drawable.download1, "Getting Started!", "QBoard is a keyboard, which helps the users exchange information in a secure manner."));
        models.add(new Model(R.drawable.download, "Use", "Our Application enables the users to exchange selectively encrypted data, that is encryption - as per the users requirements."));
        models.add(new Model(R.drawable.download2, "Keyboard Enabling", "To use our application, you would need to give the following permissions."));
        models.add(new Model(R.drawable.download3, "Sharing", "Our Application aims to help the user share encrypted texts, images, videos etc."));
        models.add(new Model(R.drawable.download4, "Welcome to the World of Secrets", "Text Away, without having to worry about any other than the targeted person reading any of your messages :)"));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position<(adapter.getCount() -1) && position<(colors.length - 1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }
                else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}