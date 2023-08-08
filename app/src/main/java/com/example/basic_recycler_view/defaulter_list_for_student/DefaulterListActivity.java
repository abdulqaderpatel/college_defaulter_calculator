package com.example.basic_recycler_view.defaulter_list_for_student;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class DefaulterListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db;

    StudentAdapter studentAdapter;

    ArrayList<Student> studentArrayList;

    String database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defaulter_list);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseFirestore.getInstance();
        studentArrayList=new ArrayList<Student>();
        studentAdapter=new StudentAdapter(this,studentArrayList,database);
        recyclerView.setAdapter(studentAdapter);

        EventChangeListener();



    }

    public void EventChangeListener()
    {

        db.collection("TOC").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null)
                {
                    Log.e("Firestore error",error.getMessage());
                    return;
                }

                for(DocumentChange dc:value.getDocumentChanges()){
                    if(dc.getType()==DocumentChange.Type.ADDED)
                    {
                        studentArrayList.add(dc.getDocument().toObject(Student.class));
                    }
                    studentAdapter.notifyDataSetChanged();
                }

            }
        });
    }
}