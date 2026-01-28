package com.example.myapplicationproject;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class eventPlannerAgenda  extends AppCompatActivity {

    Traitement t=new Traitement();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventplanneragenda);
        ImageButton back=findViewById(R.id.back);
        t.GotoDest(this,eventPlanner.class,back);
        t.test(eventPlannerAgenda.this);

    }
}
