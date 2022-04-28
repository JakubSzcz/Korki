package com.example.korki.ui.students;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.korki.R;
import com.example.korki.databinding.FragmentAddStudentBinding;
import engine.Student;
import engine.Teacher;


public class AddStudentFragment extends Fragment {

    // defaults
    private FragmentAddStudentBinding binding;

    // variables

    EditText firstName;

    EditText surname;

    EditText email;

    EditText phone;

    EditText description;

    Button cancelBut;

    Button addBut;

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

        binding = FragmentAddStudentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //initialization
        firstName = binding.firstNameEdit;
        surname = binding.surnameEdit;
        phone = binding.phoneEdit;
        email = binding.emailEdit;
        description = binding.descriptionEdit;
        cancelBut = binding.cancelBut;
        addBut = binding.addBut;

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

        //add button
        addBut.setBackgroundColor(addBut.getContext().getResources().getColor(R.color.apply_button));
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checking if required fields are filled
                boolean emptyFields = false;
                if(firstName.getText().toString().equals("")){
                    emptyFields = true;
                }
                if(surname.getText().toString().equals("")){
                    emptyFields = true;
                }
                if(email.getText().toString().equals("")){
                    emptyFields = true;
                }
                if(phone.getText().toString().equals("")){
                    emptyFields = true;
                }

                //creating new student if required fields are filled and adding it to teacher.students
                if(!emptyFields){
                    Student studentToAdd = new Student(firstName.getText().toString(),surname.getText().toString(),
                            phone.getText().toString(),email.getText().toString(),description.getText().toString());
                    Teacher.getTeacher().addStudent(studentToAdd);
                    Toast.makeText(getContext(),"New student has been added.",Toast.LENGTH_LONG).show();
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                    new StudentsFragment())
                            .addToBackStack(null)
                            .setReorderingAllowed(true)
                            .commit();
                }else {
                    Toast.makeText(getContext(),"Not all required fields were filled.",Toast.LENGTH_LONG).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return root;
    }
}
