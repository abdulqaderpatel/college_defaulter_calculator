package com.example.basic_recycler_view.student_list_for_attendance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.basic_recycler_view.R;
import com.example.basic_recycler_view.student_list_for_attendance.User;
import com.example.basic_recycler_view.student_list_for_attendance.UserAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    ArrayList<User> userArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    UserAdapter userAdapter;


    Button add, cancel;

    FirebaseFirestore db;

    String subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_student);

        Intent intent = getIntent();
        subject = intent.getStringExtra("subject");
        Toast.makeText(this, subject, Toast.LENGTH_LONG).show();


        recyclerView = findViewById(R.id.recyclerView);
        add = findViewById(R.id.add);
        cancel = findViewById(R.id.cancel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();

        userAdapter = new UserAdapter(this, userArrayList, subject);
        recyclerView.setAdapter(userAdapter);

        EventChangeListener();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void EventChangeListener() {

        db.collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        userArrayList.add(dc.getDocument().toObject(User.class));
                    }
                    userAdapter.notifyDataSetChanged();
                }

            }
        });
    }
}