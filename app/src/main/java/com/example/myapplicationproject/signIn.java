package com.example.myapplicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class signIn extends AppCompatActivity {



    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    EditText etEmail;
    EditText etPassword;
    TextView goToSignUp;
    Button loginButton;
    ImageButton goToWelcome;
    Traitement t=new Traitement();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        etEmail = findViewById(R.id.emailLogIn);
        etPassword = findViewById(R.id.pwdLoginIn);

        progressDialog = new ProgressDialog(signIn.this);
        progressDialog.setMessage("Logging In");
        progressDialog.setTitle("Please wait ");
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth = FirebaseAuth.getInstance();


        // change eye
        setEye();
        loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (!email.matches(emailPattern)) {
                    etEmail.setError("Enter context Email");
                } else if (password.isEmpty() || email.isEmpty()) {

                    Toast.makeText(signIn.this, "Champs is empty", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    etPassword.setError("Password would be under 6 charachter");

                } else {
                    progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signIn.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();

                                        // Login successful, update UI with the signed-in user's information
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Toast.makeText(signIn.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                        // Navigate to the next activity
                                        Intent intent = new Intent(signIn.this, account.class);
                                        startActivity(intent);
                                    } else {
                                        progressDialog.dismiss();

                                        // Login failed, display a message to the user.
                                        Toast.makeText(signIn.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        goToSignUp=findViewById(R.id.goToSignUp);
        t.GotoDest(this,registerSignUp.class,goToSignUp);
        goToWelcome=findViewById(R.id.backToWelcome);
        t.GotoDest(this, welcome.class,goToWelcome);

    }

    private void setEye()
    {
        ImageButton eye=findViewById(R.id.password_toggle_button);
        EditText login=findViewById(R.id.pwdLoginIn);
        eye.setTag("notVisible");
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