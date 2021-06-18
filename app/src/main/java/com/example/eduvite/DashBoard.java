package com.example.eduvite;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

        toolbarItems();

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary, getTheme()));

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

    public void toolbarItems() {
        Toolbar myToolbar = findViewById(R.id.toolbar);
        myToolbar.setVerticalScrollBarEnabled(true);
        setSupportActionBar(myToolbar);

        ImageView profile_pic = (ImageView) findViewById(R.id.profile_pic);
        profile_pic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });
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
                // This function takes you to the dashboard
                startActivity(new Intent(getBaseContext(), DashBoard.class));
                break;
            case R.id.explore_nav_icon:
                // Inflate and activate the search bar
                startActivity(new Intent(getBaseContext(), ExploreFragment.class));
                break;
            case R.id.courses_nav_icon:
                // This action takes you to the saved courses activity
                startActivity(new Intent(getBaseContext(), SavedCourseFragment.class));
                break;
            case R.id.notif_nav_icon:
                startActivity(new Intent(getBaseContext(), NotificationFragment.class));
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
