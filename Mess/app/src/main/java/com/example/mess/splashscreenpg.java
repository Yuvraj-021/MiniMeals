package com.example.mess;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;

public class splashscreenpg extends AppCompatActivity {
   public static int splash_time_out=4000;
   ConstraintLayout spl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgsplashscreen);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        spl=(ConstraintLayout)findViewById(R.id.spl);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),login_act.class);
                startActivity(intent);
                finish();
            }
        },splash_time_out);
    }
}