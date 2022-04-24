package com.example.korki.ui.students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.korki.databinding.FragmentStudentsBinding;
import com.example.korki.ui.appointments.AddAppointmentFragment;
import com.example.korki.ui.appointments.AppointmentsAdapter;
import com.example.korki.ui.students.StudentsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import engine.Student;
import engine.Teacher;

import java.util.ArrayList;
import java.util.Objects;

public class StudentsFragment extends Fragment {

    private StudentsViewModel studentsViewModel;
    private FragmentStudentsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // android defaults
        studentsViewModel =
                new ViewModelProvider(this).get(StudentsViewModel.class);

        binding = FragmentStudentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // students list
        final ListView studentsList = binding.studentsList;
        final ArrayList<Student> students = Teacher.getTeacher().getStudents();

        StudentsAdapter studentsAdapter = new StudentsAdapter(
                Objects.requireNonNull(this.getContext()), students);
        studentsList.setAdapter(studentsAdapter);


        // add student button
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


        // return
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}