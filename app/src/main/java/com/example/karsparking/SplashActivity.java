package com.example.karsparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    private static int splashTimeOut = 4000;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_splash);

            startActivity(new Intent(this,Login.class));
            finish();

//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    // This method will be executed once the timer is over
//                    Intent i = new Intent(SplashActivity.this, Login.class);
//                    startActivity(i);
//                    finish();
//                }
//            }, splashTimeOut);
        }
}
