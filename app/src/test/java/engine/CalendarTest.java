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

    @Test
    public void getFirstFutureAppointments(){
        // teacher
        Teacher teacher = Teacher.getTeacher();
        // student
        Student student = new Student("A", "C", "C", "D");
        // appointments
        teacher.addSingleAppointment(student, "Meet1", 0, LocalDateTime.now().plusWeeks(1), 90);

        // result
        ArrayList<Appointment> result = teacher.getFirstFutureAppointments(3);
        // tests
        assertEquals(1, result.size());
        assertEquals("Meet1", result.get(0).getTitle());

        // add new
        teacher.addSingleAppointment(student, "Meet2", 0, LocalDateTime.now().plusWeeks(2), 90);
        teacher.addSingleAppointment(student, "Meet3", 0, LocalDateTime.now().plusWeeks(3), 90);
        teacher.addSingleAppointment(student, "Meet4", 0, LocalDateTime.now().plusWeeks(4), 90);

        // result
        result = teacher.getFirstFutureAppointments(3);
        // tests
        assertEquals(3, result.size());
        assertEquals("Meet1", result.get(0).getTitle());
        assertEquals("Meet2", result.get(1).getTitle());
        assertEquals("Meet3", result.get(2).getTitle());

        // add new
        teacher.addSingleAppointment(student, "Meet5", 0, LocalDateTime.now().plusDays(1), 90);
        teacher.addSingleAppointment(student, "Meet6", 0, LocalDateTime.now().minusDays(1), 90);

        // result
        result = teacher.getFirstFutureAppointments(3);
        // tests
        assertEquals(3, result.size());
        assertEquals("Meet5", result.get(0).getTitle());
        assertEquals("Meet1", result.get(1).getTitle());
        assertEquals("Meet2", result.get(2).getTitle());

    }
}