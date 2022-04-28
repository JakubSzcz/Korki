package com.example.korki.ui.students;

import android.os.Bundle;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.example.korki.R;
import com.example.korki.databinding.FragmentAddStudentBinding;



public class AddStudentFragment extends Fragment {

    // defaults
    private StudentsViewModel addStudentViewModel;

    private FragmentAddStudentBinding binding;

    // variables

    EditText firstName = binding.firstNameEdit;

    EditText surname = binding.surnameEdit;

    EditText email = binding.emailEdit;

    EditText phone = binding.phoneEdit;

    EditText description = binding.descriptionEdit;

    public AddStudentFragment() {
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
        addStudentViewModel =
                new ViewModelProvider(this).get(StudentsViewModel.class);

        binding = FragmentAddStudentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student, container, false);
    }
}