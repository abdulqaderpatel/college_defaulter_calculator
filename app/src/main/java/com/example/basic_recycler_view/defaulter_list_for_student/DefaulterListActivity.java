package com.example.basic_recycler_view.defaulter_list_for_student;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.basic_recycler_view.R;
import com.example.basic_recycler_view.defaulter_list_for_student.Student;
import com.example.basic_recycler_view.defaulter_list_for_student.StudentAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class DefaulterListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db;

    StudentAdapter studentAdapter;

    ArrayList<Information> informationArrayList;
    ArrayList<Attendance> attendanceArrayList;

    String database;
    String subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defaulter_list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        informationArrayList = new ArrayList<Information>();
        attendanceArrayList = new ArrayList<Attendance>();
        Intent intent=getIntent();
        subject= intent.getStringExtra("subject");
        studentAdapter = new StudentAdapter(this, informationArrayList, attendanceArrayList, database);
        recyclerView.setAdapter(studentAdapter);

        EventChangeListener();


    }

    public void EventChangeListener() {

        db.collection("Subjects").document(subject).collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        attendanceArrayList.add(dc.getDocument().toObject(Attendance.class));
                    }
                    studentAdapter.notifyDataSetChanged();
                }

            }
        });

        db.collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        informationArrayList.add(dc.getDocument().toObject(Information.class));
                    }
                    studentAdapter.notifyDataSetChanged();
                }

            }
        });
    }
}