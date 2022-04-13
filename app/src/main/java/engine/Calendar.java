package engine;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calendar {
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    // list of appointments
    private ArrayList<Appointment> appointments;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    // constructor
    Calendar(){
        appointments = new ArrayList<>();
    }

    //
    public Boolean addAppointment(String title, LocalDateTime dateStart, int duration, float price){
        // TODO
        return true;
    }

    //
    public Boolean deleteAppointment(){
        // TODO
        return true;
    }
}
