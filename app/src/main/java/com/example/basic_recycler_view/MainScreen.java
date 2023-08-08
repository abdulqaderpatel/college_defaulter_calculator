package com.example.basic_recycler_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basic_recycler_view.defaulter_list_for_student.DefaulterListActivity;
import com.example.basic_recycler_view.student_list_for_attendance.StudentListActivity;
import com.example.basic_recycler_view.subjects.SubjectList;

public class MainScreen extends AppCompatActivity {
    Button take,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        take=findViewById(R.id.take);
        view=findViewById(R.id.view);

        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(), SubjectList.class);
            startActivity(intent);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), DefaulterListActivity.class);
                startActivity(intent);
            }
        });
    }
}