package com.example.eduvite.Adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.eduvite.ModelClass;
import com.example.eduvite.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SmallCardAdapter extends RecyclerView.Adapter<SmallCardAdapter.MyViewholder> {
    private List<ModelClass> myCourses;
    private Context context;

    public SmallCardAdapter(List<ModelClass> myCourses) {
        this.myCourses = myCourses;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_card, parent, false);
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        ModelClass course = myCourses.get(position);
        holder.courseText.setText(course.getmCourseName());
        Picasso.get().load(course.getmImage()).into(holder.courseImage);

    }

    @Override
    public int getItemCount() {
        return myCourses.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        public TextView courseText;
        public ImageView courseImage;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            courseImage = itemView.findViewById(R.id.smallcardImage);
            courseText = itemView.findViewById(R.id.smalcardtext);
            context = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Feaature Under Construction", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

