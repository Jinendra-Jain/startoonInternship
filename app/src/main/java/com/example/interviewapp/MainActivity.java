package com.example.interviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Timer vTimerFlashScreenActivity = new Timer();
        vTimerFlashScreenActivity.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent vLoginIntentMainActivity = new Intent(MainActivity.this, Login.class);
                startActivity(vLoginIntentMainActivity);
                finish();
            }
        }, 1500);

    }
}