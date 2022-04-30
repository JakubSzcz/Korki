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
import com.example.korki.databinding.FragmentEditStudentBinding;
import com.example.korki.ui.appointments.AppointmentsFragment;
import engine.Student;
import engine.Teacher;

public class EditStudentFragment extends Fragment {

    // variables
    //binding
    private FragmentEditStudentBinding binding;

    Student student;
    EditText firstName;

    EditText surname;

    EditText email;

    EditText phone;

    EditText description;

    Button cancelBut;

    Button editBut;

    //functions
    //constructors
    public EditStudentFragment() {
        //empty constructor required
    }
    public EditStudentFragment(Student student) {this.student = student;}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(student == null){
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main,
                            new StudentsFragment())
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
        }
        //creating view
        binding = FragmentEditStudentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //initialization
        firstName = binding.firstNameEdit;
        surname = binding.surnameEdit;
        phone = binding.phoneEdit;
        email = binding.emailEdit;
        description = binding.descriptionEdit;
        cancelBut = binding.cancelBut;
        editBut = binding.editBut;

        //fulfil fields with content
        if(!(student == null)) {
            firstName.setText(student.getFirstName());
            surname.setText(student.getSurName());
            phone.setText(student.getPhone());
            email.setText(student.getEmail());

            if (!student.getDescription().equals("")) {
                description.setText(student.getDescription());
            }
        }

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

        //edit button
        editBut.setBackgroundColor(editBut.getContext().getResources().getColor(R.color.apply_button));
        editBut.setOnClickListener(new View.OnClickListener() {
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

                //editing student if required fields are filled and adding it to teacher.students
                if(!emptyFields){

                    student.setFirstName(firstName.getText().toString());
                    student.setSurName(surname.getText().toString());
                    student.setEmail(email.getText().toString());
                    student.setPhone(phone.getText().toString());
                    student.setDescription(description.getText().toString());
                    Teacher.getTeacher().saveStudents();
                    Toast.makeText(getContext(),"Student has been edited.",Toast.LENGTH_LONG).show();
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

        return root;
    }
}