package com.example.eduvite.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.eduvite.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.myHolder> {
    private List<String> fileNames;
    private List<String> fileUrl;
    RecyclerView recyclerView;
    Context context;
    private String imageUrl;

    public FileAdapter(Context context, List<String> fileNames, List<String> fileUrl, RecyclerView recyclerView, String imageUrl) {
        this.fileNames = fileNames;
        this.fileUrl = fileUrl;
        this.recyclerView = recyclerView;
        this.context = context;
        this.imageUrl = imageUrl;
    }


    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_item, parent, false);
        return new FileAdapter.myHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.fileName.setText(fileNames.get(position));
        Picasso.get().load(imageUrl).into(holder.fileImage);

    }

    @Override
    public int getItemCount() {
        return fileNames.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        public TextView fileName;
        public ImageView fileImage;


        public myHolder(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.textView2);
            fileImage = itemView.findViewById(R.id.carddisplayImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerView.getChildLayoutPosition(view);

                    Intent intent = new Intent();
                    intent.setDataAndType(Uri.parse(fileUrl.get(position)), Intent.ACTION_VIEW);
                    context.startActivity(intent);



                }
            });
        }
    }
}

