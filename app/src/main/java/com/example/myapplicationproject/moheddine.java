package com.example.myapplicationproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class moheddine extends AppCompatActivity
{
    ImageButton back;
    Traitement t=new Traitement();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moheddine);
        back = findViewById(R.id.back);
        t.GotoDest(this,finance.class,back);
        FloatingActionButton fab = findViewById(R.id.fab);
        t.GotoDest(this,Add.class,fab);
        t.test(moheddine.this);

    }


}

