package com.example.korki.ui.assignments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import com.example.korki.R;
import engine.Assignment;
import engine.Teacher;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AssignmentsAdapters extends ArrayAdapter<Assignment> {

    //variables
    private final FragmentManager fragmentManager;
    private final Resources resources;

    //constructor
    public AssignmentsAdapters(@NonNull @NotNull Context context, ArrayList<Assignment> assignments, FragmentManager fragmentManager,
                               Resources resources) {
        super(context, 0, assignments);
        this.fragmentManager = fragmentManager;
        this.resources = resources;
    }

    // override getView method
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get data
        Assignment assignment = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.assignment,
                    parent, false);
        }
        // Lookup view for data population
        TextView name = convertView.findViewById(R.id.assignment_name);
        TextView added = convertView.findViewById(R.id.assignment_added);
        Button editBut = convertView.findViewById(R.id.edit_assignment_button);
        Button deleteBut = convertView.findViewById(R.id.delete_assignment_button);
        editBut.setBackgroundColor(resources.getColor(R.color.apply_button));
        deleteBut.setBackgroundColor(resources.getColor(R.color.cancel_button));

        //edit button listener
        editBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Teacher.getTeacher().getAssignments().size() > 0){
                    fragmentManager
                            .beginTransaction()
                            .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                    new EditAssignmentFragment(assignment))
                            .addToBackStack(null)
                            .setReorderingAllowed(true)
                            .commit();
                }
            }
        });

        //delete button listener
        deleteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Teacher.getTeacher().getAssignments().size() > 0){
                    //delete assignment function
                    Teacher.getTeacher().deleteAssignment(assignment);
                    //toast message
                    Toast.makeText(getContext(),"Assignment has been deleted",Toast.LENGTH_LONG).show();
                    //replacing new fragment
                    fragmentManager
                            .beginTransaction()
                            .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                    new AssignmentsFragment())
                            .addToBackStack(null)
                            .setReorderingAllowed(true)
                            .commit();
                }
            }
        });

        // Populate the data into the template view using the data object
        name.setText(assignment.getAssignmentName());
        added.setText( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").format(assignment.getAdded()));

        //show assignment details
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new ShowAssignmentFragment(assignment))
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        return convertView;
    }
}
