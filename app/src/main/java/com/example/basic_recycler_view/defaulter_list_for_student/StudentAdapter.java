package com.example.basic_recycler_view.defaulter_list_for_student;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basic_recycler_view.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    Context context;
    ArrayList<Information> informationArrayList;
    ArrayList<Attendance> attendanceArrayList;

    String database;

    public StudentAdapter(Context context, ArrayList<Information> informationArrayList, ArrayList<Attendance> attendanceArrayList, String database) {
        this.context = context;
        this.attendanceArrayList = attendanceArrayList;
        this.informationArrayList = informationArrayList;
        this.database = database;
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
        holder.present.setText(String.valueOf(new DecimalFormat("0").format(attendanceArrayList.get(position).present)));
        holder.absent.setText(String.valueOf(new DecimalFormat("0").format(attendanceArrayList.get(position).absent)));
        if (attendanceArrayList.get(position).present == 0 && attendanceArrayList.get(position).absent == 0) {
            holder.total.setText("0%");
        } else {
            holder.total.setText(String.valueOf(new DecimalFormat("0.#").format(attendanceArrayList.get(position).present * 100 / (attendanceArrayList.get(position).present + attendanceArrayList.get(position).absent)) + "%"));
        }

        if ((attendanceArrayList.get(position).present / (attendanceArrayList.get(position).present + attendanceArrayList.get(position).absent)) > 0.75) {
            holder.cardView.setCardBackgroundColor(Color.rgb(0, 255, 0));
            holder.name.setTextColor(Color.rgb(0,0,0));
            holder.id.setTextColor(Color.rgb(0,0,0));
            holder.absent.setTextColor(Color.rgb(0,0,0));
            holder.present.setTextColor(Color.rgb(0,0,0));
            holder.total.setTextColor(Color.rgb(0,0,0));
            holder.presentHeadline.setTextColor(Color.rgb(0,0,0));
            holder.absentHeadline.setTextColor(Color.rgb(0,0,0));
        } else if ((attendanceArrayList.get(position).present / (attendanceArrayList.get(position).present + attendanceArrayList.get(position).absent)) > 0.5 && ((attendanceArrayList.get(position).present / (attendanceArrayList.get(position).present + attendanceArrayList.get(position).absent)) < 0.75)) {
            holder.cardView.setCardBackgroundColor(Color.rgb(0, 0, 255));
            holder.name.setTextColor(Color.rgb(127, 255, 212));
            holder.id.setTextColor(Color.rgb(127, 255, 212));
            holder.absent.setTextColor(Color.rgb(127, 255, 212));
            holder.present.setTextColor(Color.rgb(127, 255, 212));
            holder.total.setTextColor(Color.rgb(127, 255, 212));
            holder.presentHeadline.setTextColor(Color.rgb(127, 255, 212));
            holder.absentHeadline.setTextColor(Color.rgb(127, 255, 212));
        } else {
            holder.cardView.setCardBackgroundColor(Color.rgb(255, 0, 0));
            holder.name.setTextColor(Color.rgb(144, 238, 144));
            holder.id.setTextColor(Color.rgb(144, 238, 144));
            holder.absent.setTextColor(Color.rgb(144, 238, 144));
            holder.present.setTextColor(Color.rgb(144, 238, 144));
            holder.total.setTextColor(Color.rgb(144, 238, 144));
            holder.presentHeadline.setTextColor(Color.rgb(144, 238, 144));
            holder.absentHeadline.setTextColor(Color.rgb(144, 238, 144));
        }


    }

    @Override
    public int getItemCount() {
        return informationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, id, present, absent, total,presentHeadline,absentHeadline;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.id);
            present = itemView.findViewById(R.id.present);
            absent = itemView.findViewById(R.id.absent);
            total = itemView.findViewById(R.id.total);
            cardView = itemView.findViewById(R.id.cardView);
            presentHeadline=itemView.findViewById(R.id.presentHeadline);
            absentHeadline=itemView.findViewById(R.id.absentHeadline);
        }
    }
}
