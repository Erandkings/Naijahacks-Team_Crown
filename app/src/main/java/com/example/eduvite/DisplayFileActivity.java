package com.example.eduvite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.eduvite.Adapters.FileAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DisplayFileActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<String> names = new ArrayList<>();
    private List<String> urls = new ArrayList<>();
    FileAdapter fileAdapter;
    String imageUrl;
    String category;
    String filename;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_file);
        Intent intent = getIntent();
         category = intent.getStringExtra("Category");

DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Uploads").child(category);
databaseReference.addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        for (DataSnapshot myShot : dataSnapshot.getChildren() ){
            filename = myShot.getKey();
            url = myShot.getValue(String.class);
            imageUrl = myShot.child("Image").getValue(String.class);

            update(filename, url, imageUrl);
        }
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});


        recyclerView = findViewById(R.id.viewFileRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        fileAdapter = new FileAdapter(getApplicationContext(),names,urls, recyclerView,imageUrl );
        recyclerView.setAdapter(fileAdapter);
    }

    public void update(String name, String url, String imageUrl){
        names.add(name);
        this.imageUrl = imageUrl;
        urls.add(url);
        fileAdapter.notifyDataSetChanged();
    }
}
