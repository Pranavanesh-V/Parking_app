package com.example.trail2;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.textfield.TextInputLayout;

public class student_detail_page extends AppCompatActivity {


    TextInputLayout textInputLayout1, textInputLayout2,textInputLayout3,textInputLayout4,textInputLayout5;
    Button img_btn1,img_btn2,img_btn3,generate,back;
    TextView head;
    String username, pho_no,department,v_name,v_no;
    private final boolean isKeyboardVisible = false;
    int keyboardHeight;
    private final ConstraintSet originalConstraints = new ConstraintSet();
    ConstraintLayout constraint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_detail_page);
        constraint=findViewById(R.id.page_8);


        originalConstraints.clone(constraint);



        img_btn1=findViewById(R.id.img_btn1);
        img_btn2=findViewById(R.id.img_btn2);
        img_btn3=findViewById(R.id.img_btn3);
        head=findViewById(R.id.head3);
        generate=findViewById(R.id.Generate);
        generate.setBackgroundColor(Color.GRAY);
        back=findViewById(R.id.back);
        textInputLayout1 = findViewById(R.id.txt_inputLay1);
        textInputLayout2 =findViewById(R.id.txt_inputLay2);
        textInputLayout3 =findViewById(R.id.txt_inputLay3);
        textInputLayout4 =findViewById(R.id.txt_inputLay4);
        textInputLayout5 =findViewById(R.id.txt_inputLay5);
        textInputLayout1.setHintTextAppearance(R.style.MyTextInputLayout_FloatingLabel);
        textInputLayout2.setHintTextAppearance(R.style.MyTextInputLayout_FloatingLabel);
        textInputLayout3.setHintTextAppearance(R.style.MyTextInputLayout_FloatingLabel);
        textInputLayout4.setHintTextAppearance(R.style.MyTextInputLayout_FloatingLabel);
        textInputLayout5.setHintTextAppearance(R.style.MyTextInputLayout_FloatingLabel);

        img_btn1.setBackgroundResource(R.drawable.img_student1);
        img_btn2.setBackgroundResource(R.drawable.img_bike2);
        img_btn3.setBackgroundResource(R.drawable.img_department2);
        textInputLayout1.setVisibility(View.VISIBLE);
        textInputLayout2.setVisibility(View.VISIBLE);
        head.setText(R.string.Student_Profile);

         img_btn1.setOnClickListener(v -> {
             img_btn1.setBackgroundResource(R.drawable.img_student1);
             img_btn2.setBackgroundResource(R.drawable.img_bike2);
             img_btn3.setBackgroundResource(R.drawable.img_department2);
             textInputLayout1.setVisibility(View.VISIBLE);
             textInputLayout2.setVisibility(View.VISIBLE);
             textInputLayout3.setVisibility(View.INVISIBLE);
             textInputLayout4.setVisibility(View.INVISIBLE);
             textInputLayout5.setVisibility(View.INVISIBLE);
             head.setText(R.string.Student_Profile);

         });


         EditText txt_name=textInputLayout1.getEditText();
         EditText txt_pho=textInputLayout2.getEditText();
         TextWatcher login=new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

                 username=txt_name.getText().toString().trim();
                 pho_no =txt_pho.getText().toString().trim();
                 img_btn2.setEnabled(!username.isEmpty() && !pho_no.isEmpty());
             }

             @Override
             public void afterTextChanged(Editable s) {}

         };
         txt_name.addTextChangedListener(login);
         txt_pho.addTextChangedListener(login);

        img_btn2.setOnClickListener(v -> {
            img_btn1.setBackgroundResource(R.drawable.img_student2);
            img_btn2.setBackgroundResource(R.drawable.img_bike1);
            img_btn3.setBackgroundResource(R.drawable.img_department2);
            textInputLayout1.setVisibility(View.INVISIBLE);
            textInputLayout2.setVisibility(View.INVISIBLE);
            textInputLayout3.setVisibility(View.VISIBLE);
            textInputLayout4.setVisibility(View.VISIBLE);
            textInputLayout5.setVisibility(View.INVISIBLE);
            head.setText(R.string.Bike_Info);
        });
        EditText txt_v_name=textInputLayout3.getEditText();
        EditText txt_v_no=textInputLayout4.getEditText();
        TextWatcher login2=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                v_name=txt_v_name.getText().toString().trim();
                v_no=txt_v_no.getText().toString().trim();
                img_btn3.setEnabled(!v_name.isEmpty() && !v_no.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {}

        };
        txt_v_name.addTextChangedListener(login2);
        txt_v_no.addTextChangedListener(login2);

        //button
        img_btn3.setOnClickListener(v -> {
            img_btn1.setBackgroundResource(R.drawable.img_student2);
            img_btn2.setBackgroundResource(R.drawable.img_bike2);
            img_btn3.setBackgroundResource(R.drawable.img_department1);
            textInputLayout1.setVisibility(View.INVISIBLE);
            textInputLayout2.setVisibility(View.INVISIBLE);
            textInputLayout3.setVisibility(View.INVISIBLE);
            textInputLayout4.setVisibility(View.INVISIBLE);
            textInputLayout5.setVisibility(View.VISIBLE);
            head.setText(R.string.Your_Department);


        });

        EditText txt_v_depart=textInputLayout5.getEditText();
        TextWatcher login3=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                department=txt_v_depart.getText().toString().trim();
                generate.setEnabled(!department.isEmpty() );
                int c=Color.parseColor("#3B069F");
                generate.setBackgroundTintList(ColorStateList.valueOf(c));
            }

            @Override
            public void afterTextChanged(Editable s) {}

        };
        txt_v_depart.addTextChangedListener(login3);


        generate.setOnClickListener(v -> {
            Intent intent=new Intent(student_detail_page.this, download_QR_page.class);
            intent.putExtra("username",username);
            intent.putExtra("phone_number",pho_no);
            intent.putExtra("Vehicle_name",v_name);
            intent.putExtra("Vehicle_no",v_no);
            intent.putExtra("dept",department);

            startActivity(intent);
        });

        back.setOnClickListener(v -> finish());

        setupKeyboardVisibilityListener();
    }

    private void setupKeyboardVisibilityListener() {
        final View rootView = findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            rootView.getWindowVisibleDisplayFrame(r);
            int screenHeight = rootView.getRootView().getHeight();

            int keyboardHeight = screenHeight - r.bottom;
            boolean isKeyboardVisible = keyboardHeight > screenHeight * 0.15;

            if (isKeyboardVisible) {
                // Keyboard is visible
                // TODO: Add your code to handle keyboard visibility
                updateMarginsForElements();
            } else {
                // Keyboard is not visible
                // TODO: Add your code to handle keyboard visibility
                originalCon();
            }

            // Store the current height for comparison in the next call
            int previousHeight = r.bottom;

            // Remove the listener if you no longer need to monitor keyboard visibility
            // rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        });
    }







    private void updateMarginsForElements() {
        ConstraintLayout.LayoutParams layoutParams1 = (ConstraintLayout.LayoutParams) img_btn1.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) img_btn2.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) img_btn3.getLayoutParams();


        int leftMargin = 16; // Replace with your desired left margin value in pixels
        int topMargin = 850; // Replace with your desired top margin value in pixels
        int rightMargin = 16; // Replace with your desired right margin value in pixels
        int bottomMargin = isKeyboardVisible ? keyboardHeight : 8; // Replace with your desired bottom margin value when keyboard is visible

        int leftMargin2 = 160; // Replace with your desired left margin value in pixels
        int rightMargin2 = 160;

        int leftMargin3 = 320; // Replace with your desired left margin value in pixels
        int rightMargin3 = 16;

        layoutParams1.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        layoutParams2.setMargins(leftMargin2, topMargin, rightMargin2, bottomMargin);
        layoutParams3.setMargins(leftMargin3, topMargin, rightMargin3, bottomMargin);
        img_btn1.setLayoutParams(layoutParams1);
        img_btn2.setLayoutParams(layoutParams2);
        img_btn3.setLayoutParams(layoutParams3);

    }

    private void originalCon() {
        ConstraintLayout.LayoutParams layoutParams1 = (ConstraintLayout.LayoutParams) img_btn1.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) img_btn2.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) img_btn3.getLayoutParams();


        int leftMargin = 16; // Replace with your desired left margin value in pixels
        int topMargin = 1600; // Replace with your desired top margin value in pixels
        int rightMargin = 16; // Replace with your desired right margin value in pixels
        int bottomMargin = isKeyboardVisible ? keyboardHeight : 8; // Replace with your desired bottom margin value when keyboard is visible

        int leftMargin2 = 160; // Replace with your desired left margin value in pixels
        int rightMargin2 = 160;

        int leftMargin3 = 320; // Replace with your desired left margin value in pixels
        int rightMargin3 = 16;

        layoutParams1.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        layoutParams2.setMargins(leftMargin2, topMargin, rightMargin2, bottomMargin);
        layoutParams3.setMargins(leftMargin3, topMargin, rightMargin3, bottomMargin);
        img_btn1.setLayoutParams(layoutParams1);
        img_btn2.setLayoutParams(layoutParams2);
        img_btn3.setLayoutParams(layoutParams3);

    }


}