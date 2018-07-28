package com.example.vitor.dummycook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeScreen();
            }
        }, 1000);
    }

    private void changeScreen() {
        Intent intent = new Intent(SplashScreenActivity.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

}