package com.example.myapplicationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;


public class account extends AppCompatActivity {


    RadioButton radioPaid;
    RadioButton radioFree;
    RadioButton radioapple;
    RadioButton radiovisa;
    RadioButton radiowire;

    Button btnAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        test();

    }
    private void traitementAccount() {
        radioFree = findViewById(R.id.radioFree);
        radioPaid = findViewById(R.id.radioPaid);
        radioapple = findViewById(R.id.apple);
        radiovisa = findViewById(R.id.visa);
        radiowire = findViewById(R.id.wire);

        btnAcc = findViewById(R.id.btnAccount);
        if (radioFree.isChecked()) {
            radioPaid.setChecked(false);
            radioapple.setChecked(false);
            radiovisa.setChecked(false);
            radiowire.setChecked(false);

            btnAcc.setText("Free Account");
        } else if (radioPaid.isChecked() ) {
            radioFree.setChecked(false);

            if (radioapple.isChecked()) {
                radiovisa.setChecked(false);
                radiowire.setChecked(false);
                btnAcc.setText("Paid with Apple Pay");
            } else if (radiovisa.isChecked()) {
                radioapple.setChecked(false);
                radiowire.setChecked(false);
                btnAcc.setText("Paid with Visa");
            } else {
                radioapple.setChecked(false);
                radiovisa.setChecked(false);
                radiowire.setChecked(true);
                btnAcc.setText("Paid with Wire Transfer");
            }
        }
    }
    private void test()
    { radioFree = findViewById(R.id.radioFree);
        radioPaid = findViewById(R.id.radioPaid);
        radioapple = findViewById(R.id.apple);
        radiovisa = findViewById(R.id.visa);
        radiowire = findViewById(R.id.wire);
        btnAcc = findViewById(R.id.btnAccount);

        radioFree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radioPaid.setChecked(false);
                    radioapple.setChecked(false);
                    radiovisa.setChecked(false);
                    radiowire.setChecked(false);

                    btnAcc.setText("Free Account");
                    btnAcc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(account.this, homepage.class);

                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        });

        radioPaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radioFree.setChecked(false);
                    //par défaut
                    radioapple.setChecked(true);

                    btnAcc.setText("Paid with Apple Pay");
                }
            }
        });

        radioapple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radioPaid.setChecked(true);
                    radioFree.setChecked(false);
                    radiovisa.setChecked(false);
                    radiowire.setChecked(false);
                    btnAcc.setText("Paid with Apple Pay");
                }
            }
        });

        radiovisa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radioPaid.setChecked(true);
                    radioFree.setChecked(false);
                    radioapple.setChecked(false);
                    radiowire.setChecked(false);
                    btnAcc.setText("Paid with Visa");
                }
            }
        });

        radiowire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radioPaid.setChecked(true);
                    radioFree.setChecked(false);
                    radioapple.setChecked(false);
                    radiovisa.setChecked(false);
                    btnAcc.setText("Paid with Wire Transfer");
                }
            }
        });
    }



}
