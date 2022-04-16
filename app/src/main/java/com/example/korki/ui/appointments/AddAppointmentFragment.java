package com.example.korki.ui.appointments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.example.korki.R;
import com.example.korki.databinding.FragmentAddAppointmentBinding;
import engine.Student;
import engine.Teacher;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Objects;

public class AddAppointmentFragment extends Fragment {

    // defaults
    private AppointmentsViewModel addAppointmentViewModel;
    private FragmentAddAppointmentBinding binding;

    // vars
    ArrayList<DayOfWeek> dayOfWeekActive = new ArrayList<>();

    // how many weeks
    NumberPicker howManyWeeks;
    TextView howManyWeeksLabel;

    // duration
    NumberPicker duration;
    TextView durationLabel;

    // price
    NumberPicker price;

    // date time pickers
    DatePicker date;
    TimePicker time;

    // constructor
    public AddAppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // android defaults
        addAppointmentViewModel =
                new ViewModelProvider(this).get(AppointmentsViewModel.class);

        binding = FragmentAddAppointmentBinding.inflate(inflater, container, false);
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

        // how many weeks
        howManyWeeks = binding.howManyWeeks;
        howManyWeeksLabel = binding.howManyWeeksLabel;
        howManyWeeks.setMinValue(Teacher.MIN_HOW_MANY_WEEKS);
        howManyWeeks.setMaxValue(Teacher.MAX_HOW_MANY_WEEKS);
        howManyWeeks.setVisibility(View.GONE);
        howManyWeeksLabel.setVisibility(View.GONE);

        // duration
        duration = binding.duration;
        durationLabel = binding.durationLabel;
        duration.setMinValue(Teacher.MIN_DURATION);
        duration.setMaxValue(Teacher.MAX_DURATION);

        // price
        price = binding.price;
        price.setMinValue(Teacher.MIN_PRICE);
        price.setMaxValue(Teacher.MAX_PRICE);

        // date time pickers
        date = binding.date;
        time = binding.time;

        // week day buttons
        final Button mondayBut = binding.mondayBut;
        final Button tuesdayBut = binding.tuesdayBut;
        final Button wednesdayBut = binding.wednesdayBut;
        final Button thursdayBut = binding.thursdayBut;
        final Button fridayBut = binding.fridayBut;
        final Button saturdayBut = binding.saturdayBut;
        final Button sundayBut = binding.sundayBut;
        makeDayButtonsListeners(mondayBut, DayOfWeek.MONDAY);
        makeDayButtonsListeners(tuesdayBut, DayOfWeek.TUESDAY);
        makeDayButtonsListeners(wednesdayBut, DayOfWeek.WEDNESDAY);
        makeDayButtonsListeners(thursdayBut, DayOfWeek.THURSDAY);
        makeDayButtonsListeners(fridayBut, DayOfWeek.FRIDAY);
        makeDayButtonsListeners(saturdayBut, DayOfWeek.SATURDAY);
        makeDayButtonsListeners(sundayBut, DayOfWeek.SUNDAY);

        // title
        EditText title = binding.title;

        // add button
        final Button addButton = binding.addBut;
        addButton.setBackgroundColor(getResources().getColor(R.color.apply_button));
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dayOfWeekActive.size() == 0){
                    int day = date.getDayOfMonth();
                    int month = date.getMonth();
                    int year = date.getYear();
                    int hour = time.getHour();
                    int minute = time.getMinute();
                    int finalPrice = price.getValue();
                    int finalDuration = duration.getValue();
                    String finalTitle = title.getText().toString();
                    Student student = (Student) studentsSpinner.getSelectedItem();
                    LocalDateTime dateStart = LocalDateTime.of(year, month, day, hour, minute);
                    boolean worked = Teacher.getTeacher().getCalendar().addSingleAppointment(student,
                            finalTitle, finalPrice, dateStart, finalDuration);
                    if (worked){
                        getParentFragmentManager()
                                .beginTransaction()
                                .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                        new AppointmentsFragment())
                                .addToBackStack(null)
                                .setReorderingAllowed(true)
                                .commit();
                    }else{
                        Toast.makeText(view.getContext(), "This date is taken",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    // TODO
                }
            }
        });

        // cancel button
        final Button cancelButton = binding.cancelBut;
        cancelButton.setBackgroundColor(getResources().getColor(R.color.cancel_button));
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new AppointmentsFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });


        // Inflate the layout for this fragment
        return root;
    }

    // action after day button click
    public void dayButtonClick(Button dayButton, DayOfWeek dayOfWeek){
        if (dayOfWeekActive.contains(dayOfWeek)){
            dayOfWeekActive.remove(dayOfWeek);
            refreshDayInfoList();
            dayButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
        }else {
            dayOfWeekActive.add(dayOfWeek);
            refreshDayInfoList();
            dayButton.setBackgroundColor(getResources().getColor(R.color.day_button_active));
        }
        if (dayOfWeekActive.size() > 0){
            howManyWeeks.setVisibility(View.VISIBLE);
            howManyWeeksLabel.setVisibility(View.VISIBLE);
            duration.setVisibility(View.GONE);
            durationLabel.setVisibility(View.GONE);
            date.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
        }else{
            howManyWeeks.setVisibility(View.GONE);
            howManyWeeksLabel.setVisibility(View.GONE);
            duration.setVisibility(View.VISIBLE);
            durationLabel.setVisibility(View.VISIBLE);
            date.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
        }
    }

    // make day buttons action listener
    public void makeDayButtonsListeners(Button dayButton, DayOfWeek dayOfWeek){
        dayButton.setOnClickListener(view -> {
            dayButtonClick(dayButton, dayOfWeek);
        });
    }

    // show day info
    public void refreshDayInfoList(){
        // day info list
        ListView dayInfoList = binding.daysInfo;
        Collections.sort(dayOfWeekActive);
        ArrayAdapter<DayOfWeek> dayInfoAdapter = new DayInfoAdapter(
                Objects.requireNonNull(this.getContext()), dayOfWeekActive);
        dayInfoList.setAdapter(dayInfoAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}