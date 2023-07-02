package com.example.trail2;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class intro_page extends AppCompatActivity {

    ViewPager mSlideViewPager;

    LinearLayout mDotLayout;
    Button Start;

    TextView[] dots;
    ViewPageAdapter viewPagerAdapter;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_pages);

        skip=findViewById(R.id.skip);
        Start=findViewById(R.id.Start);
        Start.setVisibility(View.INVISIBLE);
        skip.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(intro_page.this);
            builder.setView(R.layout.pop_up);

            // Create and show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();

            // Access the ImageView in the dialog layout
            ImageView imageView = dialog.findViewById(R.id.imageView);
            if (imageView != null) {
                // Set the image resource
                imageView.setImageResource(R.drawable.pop);
            }
        });
        Start.setOnClickListener(v -> {
            Intent intent=new Intent(intro_page.this, login_page.class);
            startActivity(intent);
        });



        mSlideViewPager= findViewById(R.id.viewpage);
        mDotLayout= findViewById(R.id.indicatorLayout);

        viewPagerAdapter=new ViewPageAdapter(this);

        mSlideViewPager.setAdapter(viewPagerAdapter);

        setupIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        //intro pages check
        SharedPreferences preferences=getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        String FirstTime=preferences.getString("FirstTimeInstall","");

        if(FirstTime.equals("Yes"))
        {

            //if app was opened for the first time
            Intent intent=new Intent(intro_page.this, login_page.class);
            startActivity(intent);
        }
        else
        {
            SharedPreferences.Editor editor= preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }

    }

    public void setupIndicator(int position)
    {
        dots=new TextView[5];
        mDotLayout.removeAllViews();

        for (int i=0;i< dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(32);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
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

            if (position==4)
            {
                Start.setVisibility(View.VISIBLE);
            }
            else {
                Start.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };











}
