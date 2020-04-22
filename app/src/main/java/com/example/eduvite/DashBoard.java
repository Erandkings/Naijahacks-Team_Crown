package com.example.eduvite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.eduvite.Courses.ChemistyCourse;
import com.example.eduvite.Courses.FinanceCourse;
import com.example.eduvite.Courses.LanguageCourse;
import com.example.eduvite.Courses.PhysicsCourse;

public class DashBoard extends AppCompatActivity {
    //    private View user;
    private int behavior;
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.whiteColor));
        myToolbar.setVerticalScrollBarEnabled(true);
        setSupportActionBar(myToolbar);

        behavior = behavior + 1;


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), behavior);
        adapter.addFragment(new ChemistyCourse(), "Chemistry");
        adapter.addFragment(new FinanceCourse(), "FinanceCourse");
        adapter.addFragment(new LanguageCourse(), "Language");
        adapter.addFragment(new PhysicsCourse(), "Physics");
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), Profile.class));
        return super.onOptionsItemSelected(item);
    }
}
