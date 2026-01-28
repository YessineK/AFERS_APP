package com.example.myapplicationproject;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class finance extends AppCompatActivity
{
        Traitement t=new Traitement();
        ImageButton back,Mo;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.finance);
            back=findViewById(R.id.back);
            Mo=findViewById(R.id.imageMOha);

            t.changeHeart(this,R.id.heartMo);
            t.changeHeart(this,R.id.heartBh);
            t.changeHeart(this,R.id.heartBna);
            t.changeHeart(this,R.id.hearta);
            t.GotoDest(this,homepage.class,back);
            t.GotoDest(this,moheddine.class,Mo);
            FloatingActionButton fab = findViewById(R.id.fab);
            t.GotoDest(this,Add.class,fab);
            t.test(finance.this);

        }

}
