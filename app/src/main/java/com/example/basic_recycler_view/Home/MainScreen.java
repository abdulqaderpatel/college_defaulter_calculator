package com.example.basic_recycler_view.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.example.basic_recycler_view.AddActivity;
import com.example.basic_recycler_view.R;
import com.example.basic_recycler_view.StartActivity;
import com.example.basic_recycler_view.SubjectList;
import com.example.basic_recycler_view.SubjectListDefaulter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class MainScreen extends AppCompatActivity {

    ImageView take_attendance,check_attendance;
    ImageButton logout;
    FloatingActionButton add;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        take_attendance=findViewById(R.id.take_attendance);
        check_attendance=findViewById(R.id.check_attendance);
        add=findViewById(R.id.add);
        logout=findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();


take_attendance.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainScreen.this, SubjectList.class));
    }
});

check_attendance.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainScreen.this, SubjectListDefaulter.class));
    }
});

logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mAuth.signOut();
        startActivity(new Intent(MainScreen.this, StartActivity.class));
        finish();
    }
});

add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainScreen.this, AddActivity.class));
    }
});





    }

}