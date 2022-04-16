package com.example.korki.ui.appointments;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.example.korki.R;
import com.example.korki.databinding.FragmentAddAppointmentBinding;
import engine.Student;
import engine.Teacher;

import java.util.ArrayList;

public class AddAppointmentFragment extends Fragment {

    private AppointmentsViewModel addAppointmentViewModel;
    private FragmentAddAppointmentBinding binding;

    public AddAppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // android defaults
        addAppointmentViewModel =
                new ViewModelProvider(this).get(AppointmentsViewModel.class);

        binding = FragmentAddAppointmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // student spinner
        final Spinner studentsSpinner = binding.studentsSpinner;
        final ArrayList<Student> students = Teacher.getTeacher().getStudents();
        ArrayAdapter<Student> spinnerAdapter = new ArrayAdapter<>(
                this.getContext(),
                android.R.layout.simple_spinner_item,
                android.R.id.text1
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.addAll(students);
        spinnerAdapter.notifyDataSetChanged();
        studentsSpinner.setAdapter(spinnerAdapter);


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}