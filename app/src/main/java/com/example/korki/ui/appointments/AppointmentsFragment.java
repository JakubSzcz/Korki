package com.example.korki.ui.appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.example.korki.R;
import com.example.korki.databinding.FragmentAppointmentsBinding;
import com.example.korki.ui.home.HomeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import engine.Appointment;
import engine.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static android.os.Build.VERSION_CODES.R;

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
        for (int i = 0; i < 10; i++){
            Appointment appointment = new Appointment(student, "Meeting: " + i, 90, LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(90), 0);
            appointments.add(appointment);
        }
        ////////////

        AppointmentsAdapter appointmentsAdapter = new AppointmentsAdapter(
                Objects.requireNonNull(this.getContext()), appointments);
        appointmentsList.setAdapter(appointmentsAdapter);

        // add appointment button
        final FloatingActionButton addAppointmentBut = binding.addAppointmentBut;
        addAppointmentBut.setOnClickListener(view -> {
            // appointmentsList.setVisibility(View.GONE);
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