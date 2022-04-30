package com.example.korki.ui.students;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.korki.R;
import com.example.korki.databinding.FragmentShowStudentBinding;
import engine.Student;

public class ShowStudentFragment extends Fragment {

    //variables
    private FragmentShowStudentBinding binding;

    TextView firstName;

    TextView surname;

    TextView email;

    TextView phone;

    TextView description;

    Button cancelBut;

    Button callBut;

    Student student;

    public ShowStudentFragment(Student student) {this.student = student;}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentShowStudentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //initialization
        firstName = binding.firstNameContent;
        surname = binding.surnameContent;
        email = binding.emailContent;
        phone = binding.phoneContent;
        description = binding.descriptionContent;
        callBut = binding.callBut;
        cancelBut = binding.cancelBut;

        //filing date
        firstName.setText(student.getFirstName());
        surname.setText(student.getSurName());
        phone.setText(student.getPhone());
        email.setText(student.getEmail());
        description.setText(student.getDescription());

        //adding handling buttons logic
        //cancel button
        cancelBut.setBackgroundColor(cancelBut.getContext().getResources().getColor(R.color.cancel_button));
        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new StudentsFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        //call button
        cancelBut.setBackgroundColor(callBut.getContext().getResources().getColor(R.color.teal_700));
        callBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });


        return root;
    }
}