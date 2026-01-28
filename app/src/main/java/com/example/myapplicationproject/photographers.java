package com.example.myapplicationproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class photographers extends AppCompatActivity
{
    ImageButton retour;
    Traitement t=new Traitement();
    TableRow colonne;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        t.changeHeart(this,R.id.heartYk);
        t.changeHeart(this,R.id.heartghazi);
        t.changeHeart(this,R.id.heartMoha);
        t.changeHeart(this,R.id.heartcharfi);

        colonne=findViewById(R.id.colonne);

        t.GotoDest(this,yk.class,colonne);
        retour=findViewById(R.id.back);
        t.GotoDest(this,homepage.class,retour);
        fab = findViewById(R.id.fab);
        t.GotoDest(this,Add.class,fab);
        t.test(photographers.this);


    }


}