package com.example.eduvite.Courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.eduvite.R;

public class ChemistyCourse extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the activity_dashboard for this fragment
        return inflater.inflate(R.layout.fragment_chem, container, false);
    }

}
