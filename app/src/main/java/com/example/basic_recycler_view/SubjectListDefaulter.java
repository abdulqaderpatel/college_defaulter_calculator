package com.example.basic_recycler_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.basic_recycler_view.defaulter_list_for_student.DefaulterListActivity;
import com.example.basic_recycler_view.student_list_for_attendance.StudentListActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectListDefaulter extends AppCompatActivity {

    ListView listView;
    List<String> list;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        listView=findViewById(R.id.list);
        list=new ArrayList<>();
        db=FirebaseFirestore.getInstance();
        db.collection("Subjects").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(QueryDocumentSnapshot documentSnapshot:task.getResult())
                    {
                        list.add(documentSnapshot.getId());

                    }


                }
                ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1 ,list);
                listView.setAdapter(arr);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(getApplicationContext(), DefaulterListActivity.class);
                        intent.putExtra("subject",list.get(i));
                        startActivity(intent);
                    }
                });
            }
        });

    }
}