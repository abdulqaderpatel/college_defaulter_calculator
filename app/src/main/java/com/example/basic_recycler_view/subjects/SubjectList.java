package com.example.basic_recycler_view.subjects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.basic_recycler_view.R;
import com.example.basic_recycler_view.student_list_for_attendance.StudentListActivity;

public class SubjectList extends AppCompatActivity {
    ListView listView;
    String subjects[]
            = {"TOC","SOA","UNITY","AI","MAD"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        listView=findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subjects);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(), StudentListActivity.class);
                intent.putExtra("subject",subjects[i]);

                startActivity(intent);
            }
        });

    }
}