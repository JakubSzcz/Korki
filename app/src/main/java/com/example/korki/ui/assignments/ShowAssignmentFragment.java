package com.example.korki.ui.assignments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.korki.R;
import engine.Assignment;

public class ShowAssignmentFragment extends Fragment {

    //variables
    Assignment assignment;
    public ShowAssignmentFragment() {
        // Required empty public constructor
    }
    public ShowAssignmentFragment(Assignment assignment) {
        this.assignment = assignment;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_assignment, container, false);
    }
}