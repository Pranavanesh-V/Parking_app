package com.example.trail2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class starting_page extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Code to start the intent
            Intent intent = new Intent(starting_page.this, intro_page.class);
            startActivity(intent);
        }, 1200); // Delay in milliseconds (1.20 seconds in this example)


    }
}