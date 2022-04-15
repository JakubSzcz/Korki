package com.example.korki.ui.appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.korki.databinding.FragmentAppointmentsBinding;
import engine.Appointment;
import engine.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class AppointmentsFragment extends Fragment {

    private AppointmentsViewModel appointmentsViewModel;
    private FragmentAppointmentsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // android defaults
        appointmentsViewModel =
                new ViewModelProvider(this).get(AppointmentsViewModel.class);

        binding = FragmentAppointmentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // appointments list
        final ListView appointmentsList = binding.appointmentsList;
        ArrayList<Appointment> appointments = new ArrayList<>();

        //////////// testing purposes
        Student student = new Student("Jan", "Kowalski", "C", "D");
        Appointment appointment1 = new Appointment(student, "Meeting", 90, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(90), 0);
        Appointment appointment2 = new Appointment(student, "Meeting", 90, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(90), 0);
        appointments.add(appointment1);
        appointments.add(appointment2);
        ////////////

        AppointmentsAdapter appointmentsAdapter = new AppointmentsAdapter(
                Objects.requireNonNull(this.getContext()), appointments);
        appointmentsList.setAdapter(appointmentsAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}