package com.example.korki.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.korki.R;
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
        final ArrayList<Appointment> appointments = Teacher.getTeacher().getAppointments();
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