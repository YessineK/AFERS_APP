package com.example.myapplicationproject;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class eventPlanner  extends AppCompatActivity {

    Traitement t=new Traitement();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventplanner);
        ImageButton back=findViewById(R.id.back);
        t.GotoDest(this,homepage.class,back);
        ImageView fss=findViewById(R.id.fss);
        t.GotoDest(this,eventPlannerAgenda.class,fss);
        t.test(eventPlanner.this);

    }
}
