package com.example.korki.ui.students;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import com.example.korki.R;
import engine.Student;
import engine.Teacher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StudentsAdapter extends ArrayAdapter<Student> {
    // constructor
    private FragmentManager fragmentManager;
    public StudentsAdapter(@NonNull @NotNull Context context, ArrayList<Student> students, FragmentManager fragmentManager) {
        super(context, 0, students);
        this.fragmentManager = fragmentManager;
    }

    // override getView method
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newConvertView;
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
                    Teacher.getTeacher().deleteStudent(student);
                    fragmentManager
                            .beginTransaction()
                            .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                    new StudentsFragment())
                            .addToBackStack(null)
                            .setReorderingAllowed(true)
                            .commit();
                }else{

                }
            }
        });

        // Populate the data into the template view using the data object
        firstName.setText("First name: " + student.getFirstName());
        surName.setText("Surname: " + student.getSurName());
        //assign buttons to their roles

        // Return the completed view to render on screen
        return convertView;
    }
}