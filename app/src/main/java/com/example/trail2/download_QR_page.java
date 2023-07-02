package com.example.trail2;


import static android.widget.Toast.makeText;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class download_QR_page extends AppCompatActivity {

    ImageView qr;
    Button download;


    public static Bitmap bitmap = null;
    String v_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_qr_page);
        qr=findViewById(R.id.qr);
        download=findViewById(R.id.Download);



        Intent intent=getIntent();

        String u_name=intent.getStringExtra("username");
        String ph_no=intent.getStringExtra("phone_number");
        v_name=intent.getStringExtra("Vehicle_name");
        String v_no=intent.getStringExtra("Vehicle_no");
        String department=intent.getStringExtra("dept");

        String text=u_name+"\n"+ph_no+"\n"+v_name+"\n"+v_no+"\n"+department;
        makeText(download_QR_page.this,"Successfully generated",Toast.LENGTH_LONG).show();

        //encrypt.gen(u_name,ph_no,v_name,v_no,department);
        String encrypt=Encryption.encrypt(text);
        generate(encrypt,qr);




        download.setOnClickListener(v -> {

            saveImageToDirectory(bitmap);
            finish();

        });

    }

    public static void generate(String text, ImageView view) {
        int height = 600;
        int width = 600;


        try {
            BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    bitmap.setPixel(i, j, matrix.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setImageBitmap(bitmap);
    }

    //save the image to the folder
    //download image
    private static final String DIRECTORY_NAME = "Trail2";

    private void saveImageToDirectory(Bitmap imageBitmap) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, v_name+".jpg");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + DIRECTORY_NAME);

            ContentResolver contentResolver = getContentResolver();
            Uri imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            if (imageUri != null) {
                try {
                    OutputStream outputStream = contentResolver.openOutputStream(imageUri);
                    if (outputStream != null) {
                        // Compress and save the image to the file
                        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.close();
                        Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // Fallback for versions prior to Android 10
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), DIRECTORY_NAME);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File imageFile = new File(directory, "my_image.jpg");
            try {
                FileOutputStream outputStream = new FileOutputStream(imageFile);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
                Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
            }
        }
    }







}