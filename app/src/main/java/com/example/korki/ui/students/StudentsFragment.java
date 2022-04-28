package com.example.korki.ui.students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.korki.databinding.FragmentStudentsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import engine.Student;
import engine.Teacher;
import java.util.ArrayList;
import java.util.Objects;

public class StudentsFragment extends Fragment {

    // variables
    private FragmentStudentsBinding binding;

    //functions
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStudentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // students list
        final ListView studentsList = binding.studentsList;
        final ArrayList<Student> students = Teacher.getTeacher().getStudents();

        //students adapter
        StudentsAdapter studentsAdapter = new StudentsAdapter(
                Objects.requireNonNull(this.getContext()), students,getParentFragmentManager(),getResources());
        studentsList.setAdapter(studentsAdapter);


        // add student floating button
        final FloatingActionButton addStudentBut = binding.addStudentBut;
        addStudentBut.setOnClickListener(view -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                            new AddStudentFragment())
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
        });

        // return root view
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}