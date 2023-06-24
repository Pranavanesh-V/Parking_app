package com.example.trail2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPageAdapter extends PagerAdapter
{
    Context context;
    int[] Headings ={
            R.string.Heading1,
            R.string.Heading2,
            R.string.Heading3,
            R.string.Heading4,
            R.string.Heading5
    };
    int[] Description ={
            R.string.Desc1,
            R.string.Desc1,
            R.string.Desc1,
            R.string.Desc1,
            R.string.Desc1
    };
    int[] img={R.drawable.pg1,R.drawable.pg2,R.drawable.pg3,R.drawable.pg4,R.drawable.pg5};


    public ViewPageAdapter(Context context)
    {
        this.context=context;
    }

    public ViewPageAdapter() {
    }

    @Override
    public int getCount() {
        return Headings.length;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1=layoutInflater.inflate(R.layout.slider_layout_2,container,false);
        View view=layoutInflater.inflate(R.layout.slider_layout,container,false);
        View view2=layoutInflater.inflate(R.layout.print_slider,container,false);
        if (position==0||position==4)
        {

            TextView sliderHeading= view.findViewById(R.id.Head1);
            TextView sliderDesc= view.findViewById(R.id.Desc1);
            ImageView imageView=view.findViewById(R.id.pages1);

            sliderHeading.setText(Headings[position]);
            sliderDesc.setText(Description[position]);
            imageView.setImageResource(img[position]);
            container.addView(view);
            return view;

        }
        else if (position==2) {
            TextView sliderHeading= view2.findViewById(R.id.Head3);
            TextView sliderDesc= view2.findViewById(R.id.Desc3);
            ImageView imageView=view2.findViewById(R.id.print);

            sliderHeading.setText(Headings[position]);
            sliderDesc.setText(Description[position]);
            imageView.setImageResource(img[position]);
            container.addView(view2);
            return view2;

        }


        TextView sliderHeading= view1.findViewById(R.id.Head2);
        TextView sliderDesc= view1.findViewById(R.id.Desc2);
        ImageView imageView=view1.findViewById(R.id.pages2);

        sliderHeading.setText(Headings[position]);
        sliderDesc.setText(Description[position]);
        imageView.setImageResource(img[position]);
        container.addView(view1);
        return view1;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
