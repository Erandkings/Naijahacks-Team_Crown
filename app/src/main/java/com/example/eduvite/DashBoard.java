package com.example.eduvite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.eduvite.Courses.ChemistyCourse;
import com.example.eduvite.Courses.FinanceCourse;
import com.example.eduvite.Courses.LanguageCourse;
import com.example.eduvite.Courses.PhysicsCourse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    private int behavior;
    private ConstraintLayout constraintLayout;
    BottomNavigationView bottomNavigationView;
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary, getTheme()));

//        Toolbar myToolbar = findViewById(R.id.toolbar);
//        myToolbar.setTitleTextColor(getResources().getColor(R.color.whiteColor, getTheme()));
//        myToolbar.setVerticalScrollBarEnabled(true);
//        setSupportActionBar(myToolbar);

//        behavior = behavior + 1;

//        EditText editText = (EditText) findViewById(R.id.search_editText);
//        String search_string = editText.getText().toString();
//        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.search_ico);


        viewPager = findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), behavior);
        adapter.addFragment(new ChemistyCourse(), "Chemistry");
        adapter.addFragment(new FinanceCourse(), "FinanceCourse");
        adapter.addFragment(new LanguageCourse(), "Language");
        adapter.addFragment(new PhysicsCourse(), "Physics");
        viewPager.setAdapter(adapter);
        viewPager.getCurrentItem();

//        customToast();


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.profile_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case 1:
//                findViewById(R.id.profile_icon);
//                startActivity(new Intent(getBaseContext(), Profile.class));
//                break;
//            case 2:
////                findViewById(R.id.search_icon);
//            case 3:
//                findViewById(R.id.notification);
//                startActivity(new Intent(getBaseContext(), UserProfileActivity.class));
//                break;
//        }
////        startActivity(new Intent(getApplicationContext(), Profile.class));
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent bottomIntent;
        switch (item.getItemId()) {
            case R.id.home_nav:
                // Here nothing happens because it's the main activity
                break;
            case R.id.explore_nav:
                // Inflate and activate the search bar
                break;
            case R.id.add_nav:
                // This action takes you to the add_item activity
                break;
            case R.id.notif_nav:
                // This action takes you to the notification activity
                break;
            case R.id.courses_nav:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onStop();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //    private void customToast() {
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                constraintLayout = (ConstraintLayout) findViewById(R.id.constraint);
//                Snackbar bar = Snackbar.make(constraintLayout, "Delete Message", BaseTransientBottomBar.LENGTH_LONG);
//                bar.setActionTextColor(getResources().getColor(R.color.whiteColor, getTheme()));
//                bar.setBackgroundTint(getResources().getColor(R.color.colorPrimary, getTheme()));
//                bar.setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Snackbar.make(constraintLayout, "Message Deleted", BaseTransientBottomBar.LENGTH_LONG)
//                                .setActionTextColor(getResources().getColor(R.color.whiteColor, getTheme()))
//                                .setBackgroundTint(getResources().getColor(R.color.colorPrimary, getTheme()))
//                                .show();
//                    }
//                }).show();
//            }
//        });
//    }
}
