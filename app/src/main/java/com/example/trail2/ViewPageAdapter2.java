package com.example.trail2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPageAdapter2 extends PagerAdapter
{
    Context context;
    int[] img ={R.drawable.slider_1,
            R.drawable.sliderr_2,
            R.drawable.slider_3,
            R.drawable.slider_4 };


    public ViewPageAdapter2(Context context) {
        this.context = context;
    }
    public ViewPageAdapter2()
    {

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_lay, container, false);

        ImageView imgview=view.findViewById(R.id.imgview);
        //imgview.setImageResource(R.drawable.img);
        imgview.setImageResource(img[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }


}
