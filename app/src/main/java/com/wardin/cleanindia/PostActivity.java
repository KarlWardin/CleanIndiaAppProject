package com.wardin.cleanindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.myhexaville.smartimagepicker.ImagePicker;

import java.io.File;

public class PostActivity extends AppCompatActivity {
    ImageButton imageView1,imageView2;
    ImagePicker imagePicker;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        //ImagePicker
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker = new ImagePicker(PostActivity.this,
                        null,
                        imageUri -> {
                            imageView1.setImageURI(imageUri);
                        })
                        .setWithImageCrop(
                                1 /*aspect ratio x*/,
                                1 /*aspect ratio y*/);

                imagePicker.choosePicture(true);
               // imagePicker.openCamera();

                File file1 = imagePicker.getImageFile();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker = new ImagePicker(PostActivity.this,
                        null,
                        imageUri -> {
                            imageView2.setImageURI(imageUri);
                        })
                        .setWithImageCrop(
                                1 /*aspect ratio x*/,
                                1 /*aspect ratio y*/);

                imagePicker.choosePicture(true);
                // imagePicker.openCamera();

                File file2 = imagePicker.getImageFile();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode,requestCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_post_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                making_sure();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void making_sure(){
        
    }
}
