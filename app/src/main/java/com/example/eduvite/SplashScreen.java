package com.example.eduvite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private final int splash = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), OnBoarding.class));
            }
        }, splash);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onStop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
