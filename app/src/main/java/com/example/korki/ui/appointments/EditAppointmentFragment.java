package com.example.korki.ui.appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.example.korki.R;
import com.example.korki.databinding.FragmentEditAppointmentBinding;
import engine.Appointment;
import engine.Student;
import engine.Teacher;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EditAppointmentFragment extends Fragment {

    // defaults
    private FragmentEditAppointmentBinding binding;

    // duration
    NumberPicker duration;
    TextView durationLabel;

    // price
    NumberPicker price;

    // date time pickers
    DatePicker date;
    TimePicker time;

    // appointment
    Appointment appointment;

    // constructor
    public EditAppointmentFragment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // android defaults
        binding = FragmentEditAppointmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // student spinner
        final Spinner studentsSpinner = binding.studentsSpinner;
        final ArrayList<Student> students = Teacher.getTeacher().getStudents();
        ArrayAdapter<Student> spinnerAdapter = new ArrayAdapter<>(
                this.getContext(),
                android.R.layout.simple_spinner_item,
                android.R.id.text1
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.addAll(students);
        spinnerAdapter.notifyDataSetChanged();
        studentsSpinner.setAdapter(spinnerAdapter);
        studentsSpinner.setSelection(spinnerAdapter.getPosition(appointment.getStudent()));

        // duration
        duration = binding.duration;
        durationLabel = binding.durationLabel;
        duration.setMinValue(Teacher.MIN_DURATION);
        duration.setMaxValue(Teacher.MAX_DURATION);
        duration.setValue((int)appointment.getDuration());

        // price
        price = binding.price;
        price.setMinValue(Teacher.MIN_PRICE);
        price.setMaxValue(Teacher.MAX_PRICE);
        price.setValue((int)appointment.getPrice());

        // date time pickers
        date = binding.date;
        LocalDateTime dateStart  = appointment.getDateStart();
        date.updateDate(dateStart.getYear(), dateStart.getMonthValue(), dateStart.getDayOfMonth());
        time = binding.time;
        time.setHour(dateStart.getHour());
        time.setMinute(dateStart.getMinute());

        // title
        EditText title = binding.title;
        title.setText(appointment.getTitle());

        // edit button
        final Button editButton = binding.editBut;
        editButton.setBackgroundColor(getResources().getColor(R.color.apply_button));
        editButton.setOnClickListener(view -> {
            int day = date.getDayOfMonth();
            int month = date.getMonth();
            int year = date.getYear();
            int hour = time.getHour();
            int minute = time.getMinute();
            int finalPrice = price.getValue();
            int finalDuration = duration.getValue();
            String finalTitle = title.getText().toString();
            Student student = (Student) studentsSpinner.getSelectedItem();
            LocalDateTime finalDateStart = LocalDateTime.of(year, month, day, hour, minute);
            Teacher.getTeacher().deleteAppointment(appointment);
            boolean worked = Teacher.getTeacher().addSingleAppointment(student,
                    finalTitle, finalPrice, finalDateStart, finalDuration);
            if (worked){
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new AppointmentsFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }else{
                Teacher.getTeacher().addSingleAppointment(appointment.getStudent(),
                        appointment.getTitle(), appointment.getPrice(),
                        appointment.getDateStart(), (int)appointment.getDuration());
                Toast.makeText(view.getContext(), "This date is taken",
                        Toast.LENGTH_LONG).show();
            }
        });

        // cancel button
        final Button cancelButton = binding.cancelBut;
        cancelButton.setBackgroundColor(getResources().getColor(R.color.cancel_button));
        cancelButton.setOnClickListener(view -> getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_main,
                        new AppointmentsFragment())
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit());

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}