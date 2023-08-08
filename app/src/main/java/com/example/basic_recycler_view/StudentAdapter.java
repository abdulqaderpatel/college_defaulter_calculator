package com.example.basic_recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    Context context;
    ArrayList<Student> studentArrayList;

    public StudentAdapter(Context context, ArrayList<Student> studentArrayList) {
        this.context = context;
        this.studentArrayList = studentArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.defaulterstudentitem, parent, false);
        return new StudentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(studentArrayList.get(position).name);
        holder.id.setText(studentArrayList.get(position).id);
        holder.present.setText(String.valueOf(studentArrayList.get(position).present));
        holder.absent.setText(String.valueOf(studentArrayList.get(position).absent));
        holder.total.setText(String.valueOf(new DecimalFormat("0.#").format(studentArrayList.get(position).present * 100 / (studentArrayList.get(position).present + studentArrayList.get(position).absent)) + "%"));
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, id, present, absent, total;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.id);
            present = itemView.findViewById(R.id.present);
            absent = itemView.findViewById(R.id.absent);
            total = itemView.findViewById(R.id.total);
        }
    }
}
