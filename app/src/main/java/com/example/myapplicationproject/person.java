package com.example.myapplicationproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class person extends AppCompatActivity {

Traitement t=new Traitement();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);
        t.test(person.this);
    }
}