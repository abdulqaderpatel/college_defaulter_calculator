package com.example.basic_recycler_view.defaulter_list_for_student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basic_recycler_view.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    Context context;
    ArrayList<Information> informationArrayList;
    ArrayList<Attendance> attendanceArrayList;

    String database;

    public StudentAdapter(Context context,ArrayList<Information> informationArrayList,ArrayList<Attendance> attendanceArrayList,String database) {
        this.context = context;
        this.attendanceArrayList=attendanceArrayList;
        this.informationArrayList=informationArrayList;
        this.database=database;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.defaulterstudentitem, parent, false);
        return new StudentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(informationArrayList.get(position).name);
        holder.id.setText(informationArrayList.get(position).id);
        holder.present.setText(String.valueOf(attendanceArrayList.get(position).present));
        holder.absent.setText(String.valueOf(attendanceArrayList.get(position).absent));
        holder.total.setText(String.valueOf(new DecimalFormat("0.#").format(attendanceArrayList.get(position).present * 100 / (attendanceArrayList.get(position).present + attendanceArrayList.get(position).absent)) + "%"));
    }

    @Override
    public int getItemCount() {
        return informationArrayList.size();
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
