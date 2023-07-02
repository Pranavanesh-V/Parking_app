package com.example.trail2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
public class options_page extends AppCompatActivity {

    TextView[] dots;
    ViewPageAdapter2 viewPagerAdapter;
    ViewPager mSlideViewPager1;
    Button btn1,btn2,btn3,btn4;
    ImageView setting;
    LinearLayout mDotLayout1;
    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_page);

        btn1=findViewById(R.id.button2);
        btn2=findViewById(R.id.button3);
        btn3=findViewById(R.id.button4);
        btn4=findViewById(R.id.button5);
        setting=findViewById(R.id.setting);

        mSlideViewPager1= findViewById(R.id.viewpage1);
        mDotLayout1= findViewById(R.id.indicatorLayout1);

        viewPagerAdapter=new ViewPageAdapter2(this);

        mSlideViewPager1.setAdapter(viewPagerAdapter);

        setupIndicator(0);
        mSlideViewPager1.addOnPageChangeListener(viewListener);

        btn1.setOnClickListener(v -> {
            Intent intent=new Intent(options_page.this, student_detail_page.class);
            startActivity(intent);

        });
        btn4.setOnClickListener(v -> {
            Intent intent=new Intent(options_page.this, how_works.class);
            startActivity(intent);

        });

        setting.setOnClickListener(v -> {
            Intent intent=new Intent(options_page.this, setting_pg.class);
            startActivity(intent);
        });
        btn2.setOnClickListener(v -> {
            Intent intent=new Intent(options_page.this, View_qr.class);
            startActivity(intent);

        });

        btn3.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(options_page.this);
            builder.setView(R.layout.pop_up2);

            // Create and show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();

            // Access the ImageView in the dialog layout
            ImageView imageView = dialog.findViewById(R.id.imageView1);
            if (imageView != null) {
                // Set the image resource
                imageView.setImageResource(R.drawable.strict);
            }

        });

        // Check if the WRITE_EXTERNAL_STORAGE permission is granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            //Toast.makeText(this, "permitted", Toast.LENGTH_SHORT).show();
            System.out.println();
            // Permission is already granted, proceed with folder creation

        }

    }


    public void setupIndicator(int position)
    {
        dots=new TextView[4];
        mDotLayout1.removeAllViews();

        for (int i=0;i< dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(32);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout1.addView(dots[i]);

        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setupIndicator(position);


        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with folder creation
                Toast.makeText(this, "permitted", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied, handle accordingly
                Toast.makeText(this, "Write permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }



}