package engine;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Calendar implements Serializable {
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

    // add single appointment
    public boolean addSingleAppointment(Student student, String title, double price,
                                        LocalDateTime dateStart, int duration){
        // compute end of appointment
        LocalDateTime dateEnd = dateStart.plusMinutes(duration);
        // create appointment
        Appointment appointment = new Appointment(student, title, price, dateStart, dateEnd, 0);
        // if there is no collision add appointment to appointments list
        if (isCollision(appointment)){
            return false;
        }else{
            appointments.add(appointment);
            return true;
        }
    }

    // add periodic appointment, timeInfo = {'dayOfWeek': [LocalTime - timeStart, int - duration]}
    public boolean addPeriodicAppointment(Student student, String title, double price,
                                          HashMap<DayOfWeek, LocalTime> timeInfo,
                                          HashMap<DayOfWeek, Integer> durations,
                                          int howManyWeeks, LocalDate startingDate){
        // list for all appointments
        ArrayList<Appointment> periodicAppointment = new ArrayList<>();

        // periodic ID generator
        Random random = new Random();
        int periodicId = 0;
        while (periodicId == 0){
            periodicId = random.nextInt();
        }
        // end date
        LocalDate endDate = startingDate.plusWeeks(howManyWeeks);

        // current date for loop
        LocalDate currentDate = startingDate.plusWeeks(0);

        // days in timeInfo
        Set<DayOfWeek> days = timeInfo.keySet();

        // check for collisions
        while (!currentDate.equals(endDate)){
            if (days.contains((currentDate.getDayOfWeek()))){
                LocalTime timeStart = timeInfo.get(currentDate.getDayOfWeek());
                Integer duration = durations.get(currentDate.getDayOfWeek());
                LocalDateTime dateStart = currentDate.atTime(timeStart);
                Appointment appointment = new Appointment(student, title, price, dateStart,
                        dateStart.plusMinutes(duration), periodicId);
                if (isCollision(appointment)){
                    return false;
                }else{
                    periodicAppointment.add(appointment);
                }
            }
            currentDate = currentDate.plusDays(1);
        }
        appointments.addAll(periodicAppointment);
        return true;
    }

    // add single appointment
    public boolean deleteAppointment(Appointment appointment){
        if (appointments.contains(appointment)){
            appointments.remove(appointment);
            return true;
        }else {
            return false;
        }

    }

    // check for collisions, if collision detected returns true
    public boolean isCollision(Appointment appointment){
        for (Appointment appointmentFromList : appointments){
            if (appointmentFromList.isCollision(appointment)){
                return true;
            }
        }
        return false;
    }

    // appointments getter
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}
