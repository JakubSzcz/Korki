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
import com.example.korki.R;
import engine.Student;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StudentsAdapter extends ArrayAdapter<Student> {
    // constructor
    public StudentsAdapter(@NonNull @NotNull Context context, ArrayList<Student> students) {
        super(context, 0, students);
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
        Button editBut = convertView.findViewById(R.id.editStudentButton);
        Button deleteBut = convertView.findViewById(R.id.deleteStudentButton);

        // Populate the data into the template view using the data object
        firstName.setText("First name: " + student.getFirstName());
        surName.setText("Surname: " + student.getSurName());
        //assign buttons to their roles

        // Return the completed view to render on screen
        return convertView;
    }
}