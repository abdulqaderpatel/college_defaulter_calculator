package com.example.basic_recycler_view.add_students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basic_recycler_view.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class add_students extends AppCompatActivity {

    EditText name, id;
    Button add;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        add = findViewById(R.id.add);
        db = FirebaseFirestore.getInstance();

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (id.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Id cannot be empty", Toast.LENGTH_LONG).show();
                }
                else  if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_LONG).show();
                }
                else{
                Map<String, String> student = new HashMap<>();
                student.put("name", name.getText().toString());
                student.put("id", id.getText().toString());
                db.collection("Students").document(id.getText().toString()).set(student).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Student successfully added", Toast.LENGTH_LONG).show();
                        id.setText("");
                        name.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Student with this id already exists", Toast.LENGTH_LONG).show();
                    }
                });

                db.collection("Subjects").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> list = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                list.add(documentSnapshot.getId());
                            }
                            for (int i = 0; i < list.toArray().length; i++) {
                                Map<String, Integer> attendance = new HashMap<>();
                                attendance.put("present", 0);
                                attendance.put("absent", 0);
                                db.collection("Subjects").document(list.get(i)).collection("Students").document(id.getText().toString()).set(attendance);
                            }
                        }
                    }
                });

            }}

        });
    }
}