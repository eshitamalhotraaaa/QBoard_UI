package com.example.qboard;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class Logs extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton imagebutton;
    AlertDialog alert;
    boolean started = false;

    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        recyclerView = findViewById(R.id.recyclerview);
        s1 = getResources().getStringArray(R.array.users);
        s2 = getResources().getStringArray(R.array.description);

        RecyclerView_Adapter myAdapter = new RecyclerView_Adapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Logs");
       toolbar.setTitleTextColor(0xFFFFFFFF);

        imagebutton = findViewById(R.id.btnkeyboard);

        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                AlertDialog.Builder builder = new AlertDialog.Builder(Logs.this);
//                builder.setMessage(" Give permission to Start QBoard in your Settings.")
//                        .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                   Intent intent = new Intent(Logs.this, QBoard.class);
//                                   startActivity(intent);
//                            }
//                        }).setNegativeButton("Cancel", null);
//
//                        AlertDialog a = builder.create();
//                        a.show();

                start_stop();

            }
        });
        if(isMyServiceRunning(Encrypt_Decrypt.class)){
            started=true;
        }

    }

    public void start_stop() {
        if (checkPermission()) {
            if (started) {
                stopService(new Intent(Logs.this, Encrypt_Decrypt.class));
                started = false;
            } else {
                startService(new Intent(Logs.this, Encrypt_Decrypt.class));
                started = true;
            }
        } else {
            reqPermission();
        }
    }

    private boolean checkPermission() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {

                return false;

            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    private void reqPermission(){
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Screen Overlay Detected.");
        alertBuilder.setMessage("Enable draw over other apps' in your system settings");
        alertBuilder.setPositiveButton("OPEN SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        Uri.parse("package:" + getPackageName());
                startActivityForResult(intent, RESULT_OK);

            }
        });

        alert = alertBuilder.create();
        alert.show();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass){
        ActivityManager manager= (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if(serviceClass.getName().equals(service.getClass())){
                return true;
            }
        }
        return false;
    }

}