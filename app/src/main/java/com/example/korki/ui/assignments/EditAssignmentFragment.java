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
import com.example.korki.databinding.FragmentEditAssignmentBinding;
import engine.Assignment;
import engine.Teacher;

public class EditAssignmentFragment extends Fragment {

    // variables

    // defaults
    private FragmentEditAssignmentBinding binding;

    //variables

    EditText name;

    EditText content;

    EditText description;

    Button cancelBut;

    Button editBut;
    private Assignment assignment;

    public EditAssignmentFragment() {
        // Required empty public constructor
    }

    public EditAssignmentFragment(Assignment assignment) {
        this.assignment = assignment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(assignment == null){
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main,
                            new AssignmentsFragment())
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
        }

        //creating view
        binding = FragmentEditAssignmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //initialization
        name = binding.assignmentNameEdit;
        content = binding.assignmentContentEdit;
        description = binding.assignmentDescriptionEdit;
        cancelBut = binding.assignmentCancelBut;
        editBut = binding.assignmentEditBut;

        //fulfil fields with content
        if(!(assignment == null)){
            name.setText(assignment.getAssignmentName());
            content.setText(assignment.getContent());
            description.setText(assignment.getDescription());
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
                                new AssignmentsFragment())
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
                if(name.getText().toString().equals("")){
                    emptyFields = true;
                }
                if(description.getText().toString().equals("")){
                    emptyFields = true;
                }
                if(content.getText().toString().equals("")){
                    emptyFields = true;
                }

                //editing student if required fields are filled and adding it to teacher.students
                if(!emptyFields){

                    assignment.setAssignmentName(name.getText().toString());
                    assignment.setDescription(description.getText().toString());
                    assignment.setContent(content.getText().toString());
                    Teacher.getTeacher().save();
                    Toast.makeText(getContext(),"Assignment has been edited.",Toast.LENGTH_LONG).show();
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