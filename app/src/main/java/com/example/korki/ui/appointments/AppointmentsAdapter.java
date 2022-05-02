package com.example.korki.ui.appointments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import com.example.korki.R;
import engine.Appointment;
import engine.Teacher;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AppointmentsAdapter extends ArrayAdapter<Appointment> {
    FragmentManager fragmentManager;
    Resources resources;
    boolean drawButtons;
    // constructor
    public AppointmentsAdapter(@NonNull @NotNull Context context, ArrayList<Appointment> appointments,
                               FragmentManager fragmentManager, Resources resources, boolean drawButtons) {
        super(context, 0, appointments);
        this.fragmentManager = fragmentManager;
        this.resources = resources;
        this.drawButtons = drawButtons;
    }

    public AppointmentsAdapter(@NonNull @NotNull Context context, ArrayList<Appointment> appointments,
                               FragmentManager fragmentManager, Resources resources) {
        this(context, appointments, fragmentManager, resources, true);
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
        Button edit = convertView.findViewById(R.id.edit_button);
        Button delete = convertView.findViewById(R.id.delete_button);

        // Populate the data into the template view using the data object
        name.setText("title: " + appointment.getTitle());
        student.setText("student: " + appointment.getStudent().toString());
        dateStart.setText("date: " + DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                .format(appointment.getDateStart()));
        duration.setText("duration: " + ((int)(appointment.getDuration())) + " minutes");

        // buttons
        if (!drawButtons){
            edit.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }else{
            edit.setBackgroundColor(resources.getColor(R.color.apply_button));
            edit.setOnClickListener(view -> {
                fragmentManager
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new EditAppointmentFragment(appointment))
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            });
            delete.setBackgroundColor(resources.getColor(R.color.cancel_button));
            delete.setOnClickListener(view -> {
                Teacher.getTeacher().deleteAppointment(appointment);
                fragmentManager
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new AppointmentsFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            });
        }


        // Return the completed view to render on screen
        return convertView;
    }


}
