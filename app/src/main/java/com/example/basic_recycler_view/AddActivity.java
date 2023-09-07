package com.example.basic_recycler_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.basic_recycler_view.add_students.add_students;
import com.example.basic_recycler_view.add_subjects.add_subjects;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class AddActivity extends AppCompatActivity {

    ImageView add_student,add_subject;
    ImageButton logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add_student=findViewById(R.id.add_student);
        add_subject=findViewById(R.id.add_subject);


        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, add_students.class));
            }
        });

        add_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, add_subjects.class));
            }
        });
    }
}