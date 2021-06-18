package com.example.eduvite.Courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.eduvite.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinanceCourse extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the activity_dashboard for this fragment
        return inflater.inflate(R.layout.fragment_finance, container, false);
    }
}
