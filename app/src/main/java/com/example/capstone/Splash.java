package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide the support action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Delay of 3 seconds (3000 milliseconds)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start MainActivity after 3 seconds
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();  // Close the Splash activity
            }
        }, 3000);  // 3000 milliseconds = 3 seconds
    }
}
