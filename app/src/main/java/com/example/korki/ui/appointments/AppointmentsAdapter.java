package com.example.korki.ui.appointments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.korki.R;
import engine.Appointment;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AppointmentsAdapter extends ArrayAdapter<Appointment> {
    // constructor
    public AppointmentsAdapter(@NonNull @NotNull Context context, ArrayList<Appointment> appointments) {
        super(context, 0, appointments);
    }

    // override getView method
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Appointment appointment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.appointment,
                    parent, false);
        }
        // Lookup view for data population
        TextView name = convertView.findViewById(R.id.name);
        TextView student = convertView.findViewById(R.id.student);
        TextView dateStart = convertView.findViewById(R.id.dateStart);
        TextView duration = convertView.findViewById(R.id.duration);

        // Populate the data into the template view using the data object
        name.setText("title: " + appointment.getTitle());
        student.setText("student: " + appointment.getStudent().toString());
        dateStart.setText("date: " + DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                .format(appointment.getDateStart()));
        duration.setText("duration: " + ((int)(appointment.getDuration())) + " minutes");

        // Return the completed view to render on screen
        return convertView;
    }


}
