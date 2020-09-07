package com.kenzadev.gads_leaderboard.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kenzadev.gads_leaderboard.R;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        AppCompatImageView gadsLogo = findViewById(R.id.gads_logo);

        startActivity();
    }

    private void startActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent mainActivityIntent = new Intent(
                                            SplashScreenActivity.this,
                                            MainActivity.class);
                                    startActivity(mainActivityIntent);
                                    finish();
                                }
                            },
                2000);
    }
}