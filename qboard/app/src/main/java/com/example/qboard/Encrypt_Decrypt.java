package com.example.qboard;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class Encrypt_Decrypt extends Service implements View.OnClickListener {

    private WindowManager wm;
    private View  mFloatingView;
    private View  kapali_widget;
    private View acik_widget;
    LinearLayout ll;
    AlertDialog alert;
    boolean started = false;
    EditText editText;

    public Encrypt_Decrypt(){

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

       // editText = editText.findViewById(R.id.text);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        ll = new LinearLayout(this);
        ll.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(layoutParams);

        //getting the widget layout from xml using Layout Inflator

        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);

        //setting the layout parameters

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER;
        params.x= 0;
        params.y= 0;

        //getting windows services and adding the floating view to it
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(mFloatingView, params);

        //getting the collapsed and expanded view from the floating widget

        kapali_widget = mFloatingView.findViewById(R.id.layoutCollapsed);
        acik_widget = mFloatingView.findViewById(R.id.layoutExpanded);

        acik_widget.setOnClickListener(this);

        //adding aa touchlistner to make drag-movement of the floating widget


        mFloatingView.findViewById(R.id.relativeLayoutParent).setOnTouchListener(new View.OnTouchListener() {
            WindowManager.LayoutParams updaterpar = params;
            double x, y, px, py;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        x=updaterpar.x;
                        y=updaterpar.y;

                        px= motionEvent.getRawX();
                        py= motionEvent.getRawY();

                        return true;

                    case MotionEvent.ACTION_UP:

                        kapali_widget.setVisibility(View.GONE);
                        acik_widget.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_MOVE:

                        updaterpar.x = (int) (x+(motionEvent.getRawX()-px));
                        updaterpar.y = (int) (y+(motionEvent.getRawY()-py));
                        wm.updateViewLayout(mFloatingView, updaterpar);

                        return true;

                    default:
                        break;
                }

                return false;
            }
        });

        mFloatingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent home = new Intent(FloatingWindow.this, Encrypt_Decrypt.class);
//                home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(home);
            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        if(mFloatingView!=null) wm.removeView(mFloatingView);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.layoutExpanded:
                kapali_widget.setVisibility(View.VISIBLE);
                acik_widget.setVisibility(View.GONE);
                break;
        }

    }
}
