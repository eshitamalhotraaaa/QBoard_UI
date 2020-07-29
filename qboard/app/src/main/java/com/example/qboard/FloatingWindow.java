package com.example.qboard;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class FloatingWindow extends Service implements View.OnClickListener {

    WindowManager wm;
    LinearLayout ll;
    AlertDialog alert;
    boolean started = false;
    EditText editText;

    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // ---------------------------------------------The keyboard Floating Widget Code-----------------------------------------------
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        ll = new LinearLayout(this);
        ll.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(layoutParams);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER;
        params.x= 0;
        params.y= 0;

        ImageView openapp = new ImageView(this);
        openapp.setImageResource(R.drawable.keyboard);
        ViewGroup.LayoutParams butnparams = new ViewGroup.LayoutParams(
                300, 300);
        openapp.setLayoutParams(butnparams);

        ll.addView(openapp);
        wm.addView(ll, params);

        openapp.setOnTouchListener(new View.OnTouchListener() {
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

                        break;

                    case MotionEvent.ACTION_MOVE:

                        updaterpar.x = (int) (x+(motionEvent.getRawX()-px));
                        updaterpar.y = (int) (y+(motionEvent.getRawY()-py));

                        wm.updateViewLayout(ll, updaterpar);

                    default:
                        break;
                }

                return false;
            }
        });
    //------------------------------------------- TO ENABLE THE FLOATING ENCRYPT DECRYPT WIDGET -----------------------------------------------------------------------------


        openapp.setOnClickListener(new View.OnClickListener() {
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
        wm.removeView(ll);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.text:
                break;
            case R.id.encryptedtext:
                break;
            case R.id.close:
                stopSelf();
                break;

        }

    }
}
