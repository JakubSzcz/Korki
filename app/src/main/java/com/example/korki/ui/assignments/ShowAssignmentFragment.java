package com.example.korki.ui.assignments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.korki.R;
import com.example.korki.databinding.FragmentShowAssignmentBinding;
import com.example.korki.ui.students.StudentsFragment;
import engine.Assignment;

import java.time.format.DateTimeFormatter;

public class ShowAssignmentFragment extends Fragment {

    //variables

    // defaults
    private FragmentShowAssignmentBinding binding;

    //variables

    TextView name;

    TextView content;

    TextView description;

    TextView added;

    Button cancelBut;

    Button assignBut;

    Assignment assignment;
    public ShowAssignmentFragment() {
        // Required empty public constructor
    }
    public ShowAssignmentFragment(Assignment assignment) {this.assignment = assignment;}

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

        binding = FragmentShowAssignmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //initialization
        name = binding.assignmentNameContent;
        content = binding.assignmentContentContent;
        description = binding.assignmentDescriptionContent;
        added = binding.assignmentAddedContent;
        cancelBut = binding.assignmentCancelBut;
        assignBut = binding.assignmentAssignBut;

        //filing data
        if(!(assignment == null)) {
            name.setText(assignment.getAssignmentName());
            description.setText(assignment.getDescription());
            content.setText(assignment.getContent());
            added.setText( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").format(assignment.getAdded()));
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

        assignBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new EmailAssignmentFragment(assignment))
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        return root;
    }
}