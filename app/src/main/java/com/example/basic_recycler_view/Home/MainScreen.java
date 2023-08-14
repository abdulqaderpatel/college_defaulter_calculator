package com.example.basic_recycler_view.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;


import com.example.basic_recycler_view.R;

import java.util.ArrayList;


public class MainScreen extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<Menu> menuArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        recyclerView=findViewById(R.id.recyclerView);
        menuArrayList=new ArrayList<Menu>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuArrayList.add(new Menu(R.drawable.take_attendance,"Take Attendance"));
        menuArrayList.add(new Menu(R.drawable.check_attendance,"Check Attendance"));
        menuArrayList.add(new Menu(R.drawable.add_student,"Add student"));
        menuArrayList.add(new Menu(R.drawable.add_subject,"Add subject"));
        MainAdapter mainAdapter=new MainAdapter(this,menuArrayList);
        recyclerView.setAdapter(mainAdapter);




    }
}