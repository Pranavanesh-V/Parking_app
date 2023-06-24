package com.example.trail2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class View_qr extends AppCompatActivity {


    private static final int REQUEST_CODE_PICK_IMAGES = 2;

    private ImageView imageView;
    private String fileName;

    private TextView vehicle_n;
    String t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_qr);

        imageView=findViewById(R.id.img12);
         vehicle_n= findViewById(R.id.vehicle_n);


        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
        } else {
            // Fallback for versions prior to Android 10
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGES);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGES && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.getData() != null) {
                    // Single image selected
                    Uri imageUri = data.getData();
                    String selectedImagePath = imageUri.getPath();

                    // Retrieve the file name from the selected image path
                     fileName= selectedImagePath.substring(selectedImagePath.lastIndexOf("/") + 1,selectedImagePath.length()-4);
                    // Create ImageView and add it to the layout
                    createImageView(imageUri);


                }
            }
        }
    }

    private void createImageView(Uri imageUri) {
        try {
            Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            vehicle_n.setText(fileName);
            // Use the image bitmap as needed
            imageView.setImageBitmap(imageBitmap);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
        }
    }
}