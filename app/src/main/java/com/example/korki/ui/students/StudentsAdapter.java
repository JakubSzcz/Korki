package com.example.korki.ui.students;

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
import engine.Student;
import engine.Teacher;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class StudentsAdapter extends ArrayAdapter<Student> {
    // constructor
    private final FragmentManager fragmentManager;
    private final Resources resources;
    public StudentsAdapter(@NonNull @NotNull Context context, ArrayList<Student> students, FragmentManager fragmentManager,
    Resources resources) {
        super(context, 0, students);
        this.fragmentManager = fragmentManager;
        this.resources = resources;
    }

    // override getView method
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Student student = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student,
                    parent, false);
        }

        // Lookup view for data population
        TextView firstName = convertView.findViewById(R.id.firstname);
        TextView surName = convertView.findViewById(R.id.surName);
        Button editBut = convertView.findViewById(R.id.edit_student_button);
        Button deleteBut = convertView.findViewById(R.id.delete_student_button);
        editBut.setBackgroundColor(resources.getColor(R.color.apply_button));
        deleteBut.setBackgroundColor(resources.getColor(R.color.cancel_button));

        //edit button listener
        editBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Teacher.getTeacher().getStudents().size() > 0){
                    //TODO edit student function
                }else{

                }
            }
        });

        //delete button listener
        deleteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Teacher.getTeacher().getStudents().size() > 0){
                    //delete student function
                    Teacher.getTeacher().deleteStudent(student);
                    //toast message
                    Toast.makeText(getContext(),"Student has been deleted",Toast.LENGTH_LONG).show();
                    //replacing new fragment
                    fragmentManager
                            .beginTransaction()
                            .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                    new StudentsFragment())
                            .addToBackStack(null)
                            .setReorderingAllowed(true)
                            .commit();
                }
            }
        });

        // Populate the data into the template view using the data object
        firstName.setText("First name: " + student.getFirstName());
        surName.setText("Surname: " + student.getSurName());

        // Return the completed view to render on screen
        return convertView;
    }
}