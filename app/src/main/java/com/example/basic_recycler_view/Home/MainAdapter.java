package com.example.basic_recycler_view.Home;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basic_recycler_view.R;
import com.example.basic_recycler_view.SubjectList;
import com.example.basic_recycler_view.SubjectListDefaulter;
import com.example.basic_recycler_view.add_students.add_students;
import com.example.basic_recycler_view.add_subjects.add_subjects;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Context context;
    ArrayList<Menu> menuArrayList;

    public MainAdapter(Context context, ArrayList<Menu> menuArrayList) {
        this.context = context;
        this.menuArrayList = menuArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.mainitem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(menuArrayList.get(position).text);
        holder.image.setImageResource(menuArrayList.get(position).image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==0)
                {
                    Intent intent=new Intent(context, SubjectList.class);
                    context.startActivity(new Intent(context,SubjectList.class));
                }
               else if(position==1)
                {
                    Intent intent=new Intent(context, SubjectList.class);
                    context.startActivity(new Intent(context, SubjectListDefaulter.class));
                } else if(position==2)
                {
                    Intent intent=new Intent(context, SubjectList.class);
                    context.startActivity(new Intent(context, add_students.class));
                } else if(position==3)
                {
                    Intent intent=new Intent(context, SubjectList.class);
                    context.startActivity(new Intent(context, add_subjects.class));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView name;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text);
            image = itemView.findViewById(R.id.image);

        }
    }
}
