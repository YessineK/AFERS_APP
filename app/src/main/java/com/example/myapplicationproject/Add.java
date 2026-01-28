package com.example.myapplicationproject;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.Manifest;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Add extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST =999 ;
    EditText inputTilte, inputAddresse;
    ImageButton imageBtn;
    final int REQUEST_CODE_GALLERY = 999;
    Button add,show;
    String title,address;
    Bitmap image;
    sql dbHelper;
    private TableLayout tableLayout;
    photographers myPhotographer = new photographers();
    Traitement t=new Traitement();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);

        inputTilte = findViewById(R.id.inputTitle);
        inputAddresse = findViewById(R.id.inputAddresse);
        imageBtn = findViewById(R.id.imageAdd);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
         dbHelper = new sql(this);


        add = findViewById(R.id.addproduct);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 title = ((EditText) findViewById(R.id.inputTitle)).getText().toString();
                 address = ((EditText) findViewById(R.id.inputAddresse)).getText().toString();
                 image = ((BitmapDrawable) imageBtn.getDrawable()).getBitmap();
                long rowid=dbHelper.addData(title, address, image);
                if (rowid != -1) {
                    Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to add data", Toast.LENGTH_SHORT).show();
                }



            }
        });
        show=findViewById(R.id.showButton);
       t.GotoDest(this,photographers.class,show);


    }


sql getSql()
{
    return dbHelper;
}




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the URI of the selected image
            Uri selectedImage = data.getData();

            // Use the URI to load the image into an ImageView
            ImageView imageView = findViewById(R.id.imageAdd);
            imageView.setImageURI(selectedImage);
        }
    }
}