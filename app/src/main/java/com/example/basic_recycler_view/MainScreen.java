package com.example.basic_recycler_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.basic_recycler_view.add_students.add_students;
import com.example.basic_recycler_view.add_subjects.add_subjects;


public class MainScreen extends AppCompatActivity {
    Button take,view;
    LinearLayout take_attendance,check_attendance,add_subject,add_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        take_attendance=findViewById(R.id.take_attendance);
        check_attendance=findViewById(R.id.check_attendance);
        add_student=findViewById(R.id.add_students);
        add_subject=findViewById(R.id.add_subjects);

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), add_students.class);
                startActivity(intent);
            }
        });

        add_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), add_subjects.class);
                startActivity(intent);
            }
        });

        take_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SubjectList.class);
                startActivity(intent);
            }
        });

        check_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SubjectListDefaulter.class);
                startActivity(intent);
            }
        });




    }
}