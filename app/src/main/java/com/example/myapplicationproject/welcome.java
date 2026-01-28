package com.example.myapplicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {

    Button btnLogin,btnSignUp;
    Traitement t=new Traitement();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnLogin=findViewById(R.id.btnLoginWelcom);
        t.GotoDest(this,signIn.class,btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);
        t.GotoDest(this,registerSignUp.class,btnSignUp);
    }
}