package com.example.eduvite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class DashBoard extends AppCompatActivity {
//    private ViewPager viewPager;
    private View user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        user = (View) findViewById(R.id.imageView6);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 4);
        adapter.addFragment(new ChemistyCourse(), "Chemistry");
        adapter.addFragment(new FinanceCourse(), "FinanceCourse");
        adapter.addFragment(new LanguageCourse(), "Language");
        adapter.addFragment(new PhysicsCourse(), "Physics");
        viewPager.setAdapter(adapter);

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
