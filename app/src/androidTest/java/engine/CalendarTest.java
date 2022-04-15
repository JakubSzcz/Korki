package engine;

import org.junit.Test;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class CalendarTest {

    @Test
    public void appointmentEquals(){
        // test objects
        Student student = new Student("A", "C", "C", "D");
        Appointment appointment1 = new Appointment(student, "A", 90, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(90), 0);
        Appointment appointment2 = new Appointment(student, "A", 90, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(90), 0);
        Appointment appointment3 = new Appointment(student, "B", 90, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(90), 0);
        Appointment appointment4 = new Appointment(student, "A", 90, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(91), 0);

        // tests
        assertTrue(appointment1.equals(appointment2));
        assertFalse(appointment1.equals(appointment3));
        assertFalse(appointment1.equals(appointment4));
    }

    @Test
    public void addPeriodicAppointment() {
        // calendar object
        Calendar calendar = new Calendar();
        // variables co create appointment
        Student student = new Student("A", "C", "C", "D");
        HashMap<DayOfWeek, LocalTime> timeInfo = new HashMap<>();
        timeInfo.put(DayOfWeek.MONDAY, LocalTime.of(15,30));
        HashMap<DayOfWeek, Integer> durations = new HashMap<>();
        durations.put(DayOfWeek.MONDAY, 90);
        LocalDate startingDate = LocalDate.of(2022, Month.APRIL, 15);
        // function to test
        boolean worked = calendar.addPeriodicAppointment(student, "A", 2, timeInfo, durations,
                2, startingDate);
        // expected values
        LocalDateTime dateStart = LocalDateTime.of(2022, Month.APRIL,
                18, 15, 30);
        Appointment appointment1 = new Appointment(student, "A", 2, dateStart,
                dateStart.plusMinutes(90), 0);
        Appointment appointment2 = new Appointment(student, "A", 2, dateStart.plusWeeks(1),
                dateStart.plusWeeks(1).plusMinutes(90), 0);
        // tests
        assertTrue(worked);
        ArrayList<Appointment> appointments = calendar.getAppointments();
        assertEquals(2, appointments.size());
        assertTrue(appointment1.equals(appointments.get(0)));
        assertTrue(appointment2.equals(appointments.get(1)));

    }
}