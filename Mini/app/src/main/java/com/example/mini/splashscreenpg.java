package com.example.mini;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini.Account.Loginpg;

public class splashscreenpg extends AppCompatActivity {
   public static int splash_time_out=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgsplashscreen);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Loginpg.class);
                startActivity(intent);
                finish();
            }
        },splash_time_out);
    }
}