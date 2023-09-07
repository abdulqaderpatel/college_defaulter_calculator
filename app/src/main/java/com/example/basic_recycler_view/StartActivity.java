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

public class StartActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText email,password;
    Button login;

    TextView register;
    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainScreen.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth
                        .signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (email.getText().toString().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Email cannot be empty", Toast.LENGTH_LONG).show();
                                } else if (password.getText().toString().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_LONG).show();
                                } else {
                                    if ((task.isSuccessful())) {
                                        Toast.makeText(getApplicationContext(),
                                                        "Login successful!",
                                                        Toast.LENGTH_LONG)
                                                .show();
                                        Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                                        startActivity(intent);


                                    } else {

                                        // Registration failed
                                        Toast.makeText(
                                                        getApplicationContext(),
                                                        "Login failed!!"
                                                                + " Please try again later",
                                                        Toast.LENGTH_LONG)
                                                .show();


                                    }
                                }
                            }
                        });
            }

        });
    }

}
