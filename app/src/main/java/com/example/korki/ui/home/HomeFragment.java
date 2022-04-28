package com.example.korki.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.korki.databinding.FragmentHomeBinding;
import com.example.korki.ui.appointments.AppointmentsAdapter;
import engine.Appointment;
import engine.Teacher;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // appointments list
        final ListView appointmentsList = binding.appointmentsList;
        ArrayList<Appointment> appointments = Teacher.getTeacher().getFirstFutureAppointments(3);
        if (appointments.size() > 0){
            binding.listEmpty.setVisibility(View.GONE);
        }
        AppointmentsAdapter appointmentsAdapter = new AppointmentsAdapter(
                Objects.requireNonNull(this.getContext()), appointments, getParentFragmentManager(),
                getResources(), false);
        appointmentsList.setAdapter(appointmentsAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}