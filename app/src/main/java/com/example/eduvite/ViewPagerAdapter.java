package com.example.eduvite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList = new ArrayList<>();
    private List<String> mArray = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void addFragment(Fragment fragment, String course) {
        mList.add(fragment);
        mArray.add(course);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mArray.get(position);
    }
}
