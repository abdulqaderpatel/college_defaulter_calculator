package com.example.basic_recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    FirebaseFirestore db;

    Context context;
    ArrayList<User> userArrayList;

    public UserAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
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


            db.collection("TOC").document(String.valueOf(position + 1)).update("present", FieldValue.increment(1));


        holder.isChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.isChecked.isChecked())
                {
                    db.collection("TOC").document(String.valueOf(position+1)).update("present",FieldValue.increment(1));
                    db.collection("TOC").document(String.valueOf(position+1)).update("absent",FieldValue.increment(-1));
                }
                else{
                    db.collection("TOC").document(String.valueOf(position+1)).update("absent",FieldValue.increment(1));
                    db.collection("TOC").document(String.valueOf(position+1)).update("present",FieldValue.increment(-1));

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