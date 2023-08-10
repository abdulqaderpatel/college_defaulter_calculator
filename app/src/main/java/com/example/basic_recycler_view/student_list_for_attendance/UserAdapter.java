package com.example.basic_recycler_view.student_list_for_attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basic_recycler_view.R;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    FirebaseFirestore db;

    Context context;
    ArrayList<User> userArrayList;

    String subjects;

    public UserAdapter(Context context, ArrayList<User> userArrayList,String subjects) {
        this.context = context;
        this.userArrayList = userArrayList;
        this.subjects=subjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.personitem, parent, false);
        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        db = FirebaseFirestore.getInstance();
        holder.isChecked.setChecked(true);

        holder.name.setText(userArrayList.get(position).name);
        holder.id.setText(userArrayList.get(position).id);


            db.collection("Subjects").document(subjects).collection("Students").document(String.valueOf(position+1)).update("present", FieldValue.increment(1));


        holder.isChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.isChecked.isChecked())
                {
                    db.collection("Subjects").document(subjects).collection("Students").document(String.valueOf(position+1)).update("present",FieldValue.increment(1));
                    db.collection("Subjects").document(subjects).collection("Students").document(String.valueOf(position+1)).update("absent",FieldValue.increment(-1));
                }
                else{
                    db.collection("Subjects").document(subjects).collection("Students").document(String.valueOf(position+1)).update("absent",FieldValue.increment(1));
                    db.collection("Subjects").document(subjects).collection("Students").document(String.valueOf(position+1)).update("present",FieldValue.increment(-1));

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, id;
        CheckBox isChecked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.id);
            isChecked = itemView.findViewById(R.id.isChecked);

        }
    }
}