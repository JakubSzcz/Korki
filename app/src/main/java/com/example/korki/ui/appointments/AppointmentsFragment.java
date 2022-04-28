package com.example.korki.ui.appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.korki.databinding.FragmentAppointmentsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import engine.Appointment;
import engine.Teacher;

import java.util.ArrayList;
import java.util.Objects;


public class AppointmentsFragment extends Fragment {

    private FragmentAppointmentsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // android defaults

        binding = FragmentAppointmentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // appointments list
        final ListView appointmentsList = binding.appointmentsList;
        final ArrayList<Appointment> appointments = Teacher.getTeacher().getAllAppointments();

        AppointmentsAdapter appointmentsAdapter = new AppointmentsAdapter(
                Objects.requireNonNull(this.getContext()), appointments, getParentFragmentManager(), getResources());
        appointmentsList.setAdapter(appointmentsAdapter);

        // add appointment button
        final FloatingActionButton addAppointmentBut = binding.addAppointmentBut;
        addAppointmentBut.setOnClickListener(view -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                            new AddAppointmentFragment())
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
        });

        // return
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}