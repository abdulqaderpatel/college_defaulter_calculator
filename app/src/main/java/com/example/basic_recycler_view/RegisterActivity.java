package com.example.basic_recycler_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basic_recycler_view.Home.MainScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText email,password;
    Button register;

    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email cannot be empty", Toast.LENGTH_LONG).show();
                }
                    else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_LONG).show();
                }
                        else if (password.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password length is too short", Toast.LENGTH_LONG).show();
                } else {
                    mAuth
                            .createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),
                                                        "Registration successful!",
                                                        Toast.LENGTH_LONG)
                                                .show();
                                        Intent intent=new Intent(getApplicationContext(),MainScreen.class);
                                        startActivity(intent);


                                    } else {

                                        // Registration failed
                                        Toast.makeText(
                                                        getApplicationContext(),
                                                        "Registration failed!!"
                                                                + " Please try again later",
                                                        Toast.LENGTH_LONG)
                                                .show();


                                    }
                                }
                            });
                }
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, StartActivity.class));
            }
        });
    }

}
