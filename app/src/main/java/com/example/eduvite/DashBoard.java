package com.example.eduvite;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.eduvite.Adapters.CategoryAdapter;
import com.example.eduvite.Adapters.SmallCardAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {
    private List<ModelClass> courseList = new ArrayList<>();
    private CategoryAdapter courseAdapter;
    private SmallCardAdapter smallCardAdapter;
    private FloatingActionButton AddFile;
    List<String> names = new ArrayList<>();
    List<String> urls = new ArrayList<>();
    String name, url;
    RecyclerView recyclerView1;
    RecyclerView recyclerView;
    CategoryAdapter adapter;

//    private ViewPager viewPager;
    private View user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        AddFile = findViewById(R.id.floatingActionButton2);

        AddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this,AddFileActivity.class);
                startActivity(intent);
            }
        });


         recyclerView = findViewById(R.id.recyclerviewNo);
         recyclerView1 = findViewById(R.id.recyclerViewCard);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(courseList);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(smallCardAdapter);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataSnapshot child = dataSnapshot.child("Uploads");
       String name = child.getKey();
        names.add(name);

                DataSnapshot imageshot = child.child("Image");
               String url = imageshot.getValue(String.class);
               urls.add(url);

               adapter = new CategoryAdapter(names,urls);
               recyclerView.setAdapter(adapter);


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



    }


    private void courseData(String name, String url) {


        ModelClass course = new ModelClass(name, url);
        courseList.add(course);



        courseAdapter.notifyDataSetChanged();
        smallCardAdapter.notifyDataSetChanged();

        user = (View) findViewById(R.id.imageView6);



        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onStop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
