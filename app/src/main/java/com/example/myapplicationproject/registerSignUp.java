package com.example.myapplicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerSignUp extends AppCompatActivity
{
    EditText Inputname;
    EditText Inputpwd;
    EditText Inputmail;
    EditText Inputconfirm;
    ImageButton back;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    Traitement t=new Traitement();
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();
        if(mUser!=null){

            mUser.reload();

        }
        Button bt=findViewById(R.id.signupbtn);


        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Inputname = findViewById(R.id.name);
                Inputpwd = findViewById(R.id.pwd);
                Inputmail = findViewById(R.id.mail);
                Inputconfirm = findViewById(R.id.confirmpwd);

                String nom = Inputname.getText().toString();
                String pass = Inputpwd.getText().toString();
                String email = Inputmail.getText().toString();
                String confirm = Inputconfirm.getText().toString();


                if (!email.matches(emailPattern)) {
                    Inputmail.setError("Enter context Email");
                }else if (nom.isEmpty() || pass.isEmpty() || email.isEmpty() || confirm.isEmpty()) {

                    Toast.makeText(registerSignUp.this, "Champs is empty", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(confirm))
                {
                    Inputconfirm.setError("Password not matches");
                }
                else if (pass.length()<6){
                    Inputpwd.setError("Password would be under 6 charachter");

                }
                else {
                    progressDialog = new ProgressDialog(registerSignUp.this);

                    progressDialog.setMessage("please wait while registration ...");
                    progressDialog.setTitle("Registration");
                     progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(registerSignUp.this, new OnCompleteListener<AuthResult>()
                            {
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful())
                                    {

                                        user user=new user(nom,email);
                                        Toast.makeText(registerSignUp.this, "succed", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(registerSignUp.this, signIn.class);

                                                startActivity(intent);
                                                finish();



                                    } else {
                                       progressDialog.dismiss();
                                        Toast.makeText(registerSignUp.this, "Failed Registration"+task.getException(), Toast.LENGTH_SHORT).show();


                                    }

                                }
                            });
                }
            }
        });
        //change status eye
        setEye(R.id.pwd,R.id.Imagebutton1);
        setEye(R.id.confirmpwd,R.id.Imagebutton2);
        // retour back
        back=findViewById(R.id.backtoSignIn);
        t.GotoDest(registerSignUp.this,signIn.class,back);


    }


    private void setEye(int idNomLogin,int idNomImage)
    {
        ImageButton eye=findViewById(idNomImage);
        EditText login=findViewById(idNomLogin);
        eye.setTag("notVisible");
        //password
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eye.getTag().equals("notVisible"))
                {
                    eye.setImageResource(R.drawable.ic_baseline_visibility_24);
                    login.setInputType(InputType.TYPE_CLASS_TEXT);
                    eye.setTag("visible");
                }else{
                    eye.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    login.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eye.setTag("notVisible");

                }
            }
        });
    }

}