package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button productsBtn, monitoringBtn, guidesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        productsBtn = findViewById(R.id.bt1);
        monitoringBtn = findViewById(R.id.bt2);
        guidesBtn = findViewById(R.id.bt3);

        // Set click listeners
        productsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, products.class); // Renamed Products activity
            startActivity(intent);
        });

        monitoringBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Monitoring.class);
            startActivity(intent);
        });

        guidesBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Guides.class);
            startActivity(intent);
        });
    }
}
