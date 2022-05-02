package com.example.korki.ui.assignments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.korki.R;
import com.example.korki.databinding.FragmentAddAssignmentBinding;
import engine.Teacher;

public class AddAssignmentFragment extends Fragment {

    // defaults
    private FragmentAddAssignmentBinding binding;

    //variables

    EditText name;

    EditText content;

    EditText description;

    Button cancelBut;

    Button addBut;


    public AddAssignmentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddAssignmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //initialization
        name = binding.assignmentNameEdit;
        content = binding.assignmentContentEdit;
        description = binding.assignmentDescriptionEdit;
        cancelBut = binding.assignmentCancelBut;
        addBut = binding.assignmentAddBut;

        //adding handling buttons logic
        //cancel button
        cancelBut.setBackgroundColor(cancelBut.getContext().getResources().getColor(R.color.cancel_button));
        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new AssignmentsFragment())
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
                if(name.getText().toString().equals("")){
                    emptyFields = true;
                }
                if(content.getText().toString().equals("")){
                    emptyFields = true;
                }
                if(description.getText().toString().equals("")){
                    emptyFields = true;
                }

                //creating new student if required fields are filled and adding it to teacher.students
                if(!emptyFields){
                    Teacher.getTeacher().createAssignment(name.getText().toString(),description.getText().toString(),
                            content.getText().toString());
                    Toast.makeText(getContext(),"New assignment has been added.",Toast.LENGTH_LONG).show();
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                    new AssignmentsFragment())
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