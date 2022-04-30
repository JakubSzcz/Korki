package com.example.korki.ui.assignments;

import android.os.Bundle;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.korki.databinding.FragmentAssignmentsBinding;
import com.example.korki.ui.students.StudentsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import engine.Assignment;
import engine.Teacher;

import java.util.ArrayList;
import java.util.Objects;

public class AssignmentsFragment extends Fragment {

    // variables

    private FragmentAssignmentsBinding binding;

    //functions

    //constructor
    public AssignmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAssignmentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //data from engine
        //assignments list
        final ListView assignmentsList = binding.assignmentsList;
        final ArrayList<Assignment> assignments = Teacher.getTeacher().getAssignments();

        //assignments adapter
        AssignmentsAdapters assignmentsAdapters = new AssignmentsAdapters(
                Objects.requireNonNull(this.getContext()), assignments,getParentFragmentManager(),getResources());
        assignmentsList.setAdapter(assignmentsAdapters);

        // add assignment floating button
        final FloatingActionButton addAssignmentBut = binding.addAssignmentBut;
        addAssignmentBut.setOnClickListener(view -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                            new AddAssignmentFragment())
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
        });

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}