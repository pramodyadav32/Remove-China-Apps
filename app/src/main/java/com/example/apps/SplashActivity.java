/*
 * Decompiled with CFR 0.0.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.location.Address
 *  android.location.Geocoder
 *  android.location.Location
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.v4.app.ActivityCompat
 *  android.support.v7.app.e
 *  android.text.TextUtils
 *  android.widget.ImageView
 *  com.google.android.gms.i.c
 *  com.google.android.gms.i.g
 *  com.google.android.gms.location.LocationRequest
 *  com.google.android.gms.location.b
 *  com.google.android.gms.location.d
 *  com.google.android.gms.location.f
 *  com.ojassoft.astrosage.ui.act.SplashActivity$8
 *  com.ojassoft.astrosage.ui.act.SplashActivity$9
 *  com.ojassoft.astrosage.utils.h
 *  java.io.IOException
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Void
 *  java.util.List
 *  java.util.Locale
 */
package com.example.apps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    ImageView image_name;
    Handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image_name = findViewById(R.id.image_name);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                android.view.animation.ScaleAnimation scaleAnimation = new android.view.animation.ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
                scaleAnimation.setDuration(1000);
                image_name.startAnimation(scaleAnimation);
            }
        }, 100);
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();

            }

        }, 2000);

    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacksAndMessages(null);
        finish();
    }
}

