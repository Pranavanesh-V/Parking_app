package com.example.trail2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;


public class setting_pg extends AppCompatActivity {


    Button logOut;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_pg);

        switch1=findViewById(R.id.switch1);
        logOut=findViewById(R.id.Log_out);

    }

    public void check(View view)
    {
        if (switch1.isChecked())
        {

            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            Toast.makeText(setting_pg.this,"ON",Toast.LENGTH_LONG).show();
        }
        else
        {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            Toast.makeText(setting_pg.this,"OFF",Toast.LENGTH_LONG).show();

        }
    }

}