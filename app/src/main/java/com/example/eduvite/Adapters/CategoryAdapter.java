package com.example.eduvite.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduvite.DisplayFileActivity;
import com.example.eduvite.ModelClass;
import com.example.eduvite.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.myHolder>  {

    private List<ModelClass> myCourses;
    private List<String> myNames;
    private List<String> myUrls;
    private Context context;

    public CategoryAdapter(List<String> myNames, List<String> myUrls) {
        this.myNames = myNames;
        this.myUrls = myUrls;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.big_card,parent,false);
        return new myHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
String name = myNames.get(position);
String image = myUrls.get(position);
holder.courseText.setText(name);
        Picasso.get().load(image).into(holder.courseImage);

    }

    @Override
    public int getItemCount() {
        return myUrls.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        ImageView courseImage;
        TextView courseText;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            courseImage = itemView.findViewById(R.id.bigcardimage);
            courseText = itemView.findViewById(R.id.bigcardtext);
            context = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DisplayFileActivity.class);
                    intent.putExtra("Category", courseText.getText().toString().trim());
                }
            });
        }
    }
}
