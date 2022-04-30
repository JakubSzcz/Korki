package com.example.korki.ui.students;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.korki.R;
import com.example.korki.databinding.FragmentShowStudentBinding;
import engine.Student;
import org.jetbrains.annotations.NotNull;

public class ShowStudentFragment extends Fragment {

    //variables
    private FragmentShowStudentBinding binding;

    TextView firstName;

    TextView surname;

    TextView email;

    TextView phone;

    TextView description;

    Button cancelBut;

    Button callBut;

    Student student;

    //identifies request
    private static final int REQUEST_CALL = 1;

    //functions

    //constructors
    public ShowStudentFragment() {
        //empty constructor required
    }
    public ShowStudentFragment(Student student) {this.student = student;}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //makes phone call
    private void makePhoneCall(){
        //get phone from student object and check if it is correct
        String number = student.getPhone();
        if(number.trim().length() > 0){

            //check if permission was granted
            if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                //wasn't granted, and ask for permission
                ActivityCompat.requestPermissions(getActivity() ,new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }else{
                //was granted, make a phone call
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }else{
            //format is incorrect
            Toast.makeText(getContext(),"Enter a phone number", Toast.LENGTH_LONG).show();
        }
    }

    //ask for permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else{
                Toast.makeText(getContext(),"Permision Denied",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentShowStudentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //initialization
        firstName = binding.firstNameContent;
        surname = binding.surnameContent;
        email = binding.emailContent;
        phone = binding.phoneContent;
        description = binding.descriptionContent;
        callBut = binding.callBut;
        cancelBut = binding.cancelBut;

        //filing date
        firstName.setText(student.getFirstName());
        surname.setText(student.getSurName());
        phone.setText(student.getPhone());
        email.setText(student.getEmail());
        description.setText(student.getDescription());

        //adding handling buttons logic
        //cancel button
        cancelBut.setBackgroundColor(cancelBut.getContext().getResources().getColor(R.color.cancel_button));
        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(com.example.korki.R.id.nav_host_fragment_content_main,
                                new StudentsFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        //call button
        callBut.setBackgroundColor(callBut.getContext().getResources().getColor(R.color.teal_700));
        callBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });

        return root;
    }
}