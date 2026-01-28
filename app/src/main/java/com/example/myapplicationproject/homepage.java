package com.example.myapplicationproject;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class homepage extends AppCompatActivity {


    ImageButton GoToB,hotel;
    ImageButton GoToP;
    Traitement t=new Traitement();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        t.test(homepage.this);

        GoToB=findViewById(R.id.GoToB);
        GoToP=findViewById(R.id.GoToPhoto);
        t.GotoDest(this,finance.class,GoToB);
        t.GotoDest(this,photographers.class,GoToP);
        FloatingActionButton fab = findViewById(R.id.fab);
        t.GotoDest(this,Add.class,fab);
        hotel=findViewById(R.id.hotel);
        t.GotoDest(this,eventPlanner.class,hotel);

    }

}
