package com.example.trail2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class login_page extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        button=findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent=new Intent(login_page.this, options_page.class);
            startActivity(intent);
        });
    }
}