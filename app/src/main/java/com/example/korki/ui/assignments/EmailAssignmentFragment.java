package com.example.korki.ui.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.korki.R;
import com.example.korki.databinding.FragmentEmailAssignmentBinding;
import com.example.korki.ui.home.HomeFragment;
import engine.Assignment;
import engine.Student;
import engine.Teacher;

import java.util.ArrayList;

public class EmailAssignmentFragment extends Fragment{

    //variables

    private FragmentEmailAssignmentBinding binding;
    private ArrayList<Student> students;

    TextView assignmentName;

    Spinner studentsSpinner;

    TextView studentsEmail;

    TextView studentEmailTxt;

    TextView emailSubject;

    TextView emailSubjectTxt;

    TextView emailMessage;

    TextView emailMessageTxt;

    Button cancelBut;

    Button sendBut;

    private Assignment assignment;

    //functions
    //constructors
    public EmailAssignmentFragment() {
        // Required empty public constructor
    }

    public EmailAssignmentFragment(Assignment assignment) {
        this.students = Teacher.getTeacher().getStudents();
        this.assignment = assignment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(students == null || assignment == null){
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main,
                            new AssignmentsFragment())
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
        }

        binding = FragmentEmailAssignmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //initialization
        assignmentName = binding.assignmentNameContentEmail;
        studentsSpinner = binding.studentsSpinner;
        studentsEmail = binding.assignmentStudentEmailAddressContent;
        studentsEmail.setVisibility(View.GONE);
        studentEmailTxt = binding.assignmentStudentEmailAddressTxt;
        studentEmailTxt.setVisibility(View.GONE);
        emailSubject = binding.assignmentEmailSubjectContent;
        emailSubject.setText(assignment.getAssignmentName());
        emailSubject.setVisibility(View.GONE);
        emailSubjectTxt = binding.assignmentEmailSubjectTxt;
        emailSubjectTxt.setVisibility(View.GONE);
        emailMessage = binding.assignmentEmailMessageContent;
        emailMessage.setText(assignment.getContent());
        emailMessage.setVisibility(View.GONE);
        emailMessageTxt = binding.assignmentEmailMessageTxt;
        emailMessageTxt.setVisibility(View.GONE);
        cancelBut = binding.cancelBut;
        sendBut = binding.sendBut;

        //filing data
        if(students != null && assignment != null){
            assignmentName.setText(assignment.getAssignmentName());
            //spinner
            ArrayAdapter<Student> spinnerAdapter = new ArrayAdapter<>(
                    this.getContext(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1
            );
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAdapter.addAll(students);
            spinnerAdapter.notifyDataSetChanged();
            studentsSpinner.setAdapter(spinnerAdapter);
        }

        //handling no students scenario
        if(students != null) {
            if (students.size() == 0) {
                Toast.makeText(getContext(),"You have no students to chose from",Toast.LENGTH_LONG).show();
            }
        }
        //if student was chosen
        if(studentsSpinner.getSelectedItem() != null){
            if(students != null && assignment != null){
                Student student = (Student) studentsSpinner.getSelectedItem();
                //showing hidden fields
                studentsEmail.setVisibility(View.VISIBLE);
                studentEmailTxt.setVisibility(View.VISIBLE);
                emailSubject.setVisibility(View.VISIBLE);
                emailSubjectTxt.setVisibility(View.VISIBLE);
                emailMessage.setVisibility(View.VISIBLE);
                emailMessageTxt.setVisibility(View.VISIBLE);
                //filling remaining data
                studentsEmail.setText(student.getEmail());

            }
        }
        studentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = (Student) studentsSpinner.getSelectedItem();
                //showing hidden fields
                studentsEmail.setVisibility(View.VISIBLE);
                studentEmailTxt.setVisibility(View.VISIBLE);
                emailSubject.setVisibility(View.VISIBLE);
                emailSubjectTxt.setVisibility(View.VISIBLE);
                emailMessage.setVisibility(View.VISIBLE);
                emailMessageTxt.setVisibility(View.VISIBLE);
                //filling remaining data
                studentsEmail.setText(student.getEmail());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(),"You didn't select any student.",Toast.LENGTH_LONG).show();
            }
        });

        //adding handling buttons logic
        //cancel button
        cancelBut.setBackgroundColor(cancelBut.getContext().getResources().getColor(R.color.cancel_button));
        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new ShowAssignmentFragment(assignment))
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        //send button
        sendBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //students instance for email
                Student student = (Student) studentsSpinner.getSelectedItem();
                //new action intent
                Intent intent = new Intent(Intent.ACTION_SEND);
                //filling intent with email data
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{student.getEmail()});
                intent.putExtra(Intent.EXTRA_SUBJECT, (CharSequence) (emailSubject.getText().toString()));
                intent.putExtra(Intent.EXTRA_TEXT, (CharSequence) (emailMessage.getText().toString()));
                //narrowing down the types of application that supports sending email action
                intent.setType("message/rfc822");
                //check if there is any application installed on the device that can handle this action
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(intent);
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                    new AssignmentsFragment())
                            .addToBackStack(null)
                            .setReorderingAllowed(true)
                            .commit();

                }else{
                    Toast.makeText(getContext(),"There is no application installed supporting that action!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }

}