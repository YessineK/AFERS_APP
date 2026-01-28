package com.example.myapplicationproject;


import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Traitement {

    BottomNavigationView bottomNavigationView;

    void changeHeart(Activity activity, int Nomid)
    {

        ImageView Nom=activity.findViewById(Nomid);
        Nom.setTag("empty");
        Nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(Nom.getTag().equals("empty"))
                {
                    Nom.setImageResource(R.drawable.heart);
                    Nom.setTag("red");
                }
                else{
                    Nom.setImageResource(R.drawable.heartvide);
                    Nom.setTag("empty");
                }
            }
        });

    }
    void GotoDest(Activity activity,Class dest,View id)
    {
        id.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(activity,dest);
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }

    Intent intent;
    void test(Activity activity) {
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottomNavigationView);

        // Ajouter un écouteur d'événement pour la sélection des éléments de menu de BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.chat && !isCurrentActivityChat(activity)) {

                    // Action pour le bouton "Chat"
                    intent = new Intent(activity, chat.class);
                    activity.startActivity(intent);
                    activity.finish();
                    return true;

                } else if (item.getItemId() == R.id.home && !isCurrentActivityHome(activity)) {
                    // Action pour le bouton "Home"
                    intent = new Intent(activity, homepage.class);
                    activity.startActivity(intent);
                    activity.finish();
                    return true;

                } else if (item.getItemId() == R.id.person) {
                    intent = new Intent(activity, person.class);
                    activity.startActivity(intent);
                    activity.finish();
                    return true;
                }
                return false;
            }
        });


    }
    private boolean isCurrentActivityChat(Activity activity) {
        return activity instanceof chat;
    }

    // Vérifie si l'activité actuelle est "HomeActivity"
    private boolean isCurrentActivityHome(Activity activity) {
        return activity instanceof homepage;
    }

}
