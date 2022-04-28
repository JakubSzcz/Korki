package engine;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Teacher {
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    // list of students
    private ArrayList<Student> students;

    // calendar object which contains all appointments
    private Calendar calendar;

    // assignment list
    private ArrayList<Assignment> assignments;

    // path to storage
    private static File path;

    // singleton teacher
    private static Teacher teacher;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    // constructor
    private Teacher(){
        if (path != null){
            // read calendar
            File calendarFile = new File(path, CALENDAR_FILENAME);
            try {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(calendarFile));
                this.calendar = (Calendar)stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                this.calendar = new Calendar();
            }
            // read student
            File studentsFile = new File(path, STUDENTS_FILENAME);
            try {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(studentsFile));
                this.students = (ArrayList<Student>) stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                this.students = new ArrayList<>();
            }
            // read assignments
            File assignmentsFile = new File(path, ASSIGNMENTS_FILENAME);
            try {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(assignmentsFile));
                this.assignments = (ArrayList<Assignment>) stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                this.assignments = new ArrayList<>();
            }
        }else{
            // initialize empty
            this.calendar = new Calendar();
            this.students = new ArrayList<>();
            this.assignments = new ArrayList<>();
        }

        ////////// tests purposes
        //addStudent("Jan", "Kowalski", "jan.kowalski@example.com", "999888777");
        //addStudent("Piotr", "Nowak", "piotr.nowak@example.com", "939582757");
        //addStudent("Krzysztof", "Piątek", "krzyszotf.piatek@example.com", "869886771");
    }

    // add student to students list with students values
    public void addStudent(String firstName, String surName, String email, String phone){
        Student student = new Student(firstName, surName, email, phone);
        students.add(student);
        saveStudents();
    }

    //add student to students list with student object
    public void addStudent(Student student){
        students.add(student);
        saveStudents();
    }
    // delete student from students list
    public void deleteStudent(Student studentToDelete){
        if( students.contains(studentToDelete)){
            students.remove(studentToDelete);
        }else {
            for(Student studentToCheck : students){
                if(studentToCheck.equals(studentToDelete)){
                    students.remove(studentToCheck);
                    break;
                }
            }
        }
        saveStudents();
    }

    // create assignment with attachments
    public void createAssignment(String name, String description, String content ,ArrayList<String> attachmentsFileNames){
        Assignment assignment = new Assignment(name, description, content ,attachmentsFileNames);
        assignments.add(assignment);
        saveAssignments();
    }

    // create assignment without attachments
    public void createAssignment(String name, String description, String content){
        Assignment assignment = new Assignment(name, description, content);
        assignments.add(assignment);
        saveAssignments();
    }

    //
    public void editAssignment(){
        // TODO
    }

    //delete assignment from list
    public void deleteAssignment(Assignment assignmentToDelete){
        for(Assignment toDelete : assignments){
            if(toDelete.equals(assignmentToDelete)){
                assignments.remove(toDelete);
                break;
            }
        }
    }

    // add single appointment
    public boolean addSingleAppointment(Student student, String title, double price,
                                        LocalDateTime dateStart, int duration){
        boolean worked = calendar.addSingleAppointment(student, title, price, dateStart, duration);
        if (worked){
            saveCalendar();
        }
        return worked;
    }

    // add periodic appointment, timeInfo = {'dayOfWeek': [LocalTime - timeStart, int - duration]}
    public boolean addPeriodicAppointment(Student student, String title, double price,
                                          HashMap<DayOfWeek, LocalTime> timeInfo,
                                          HashMap<DayOfWeek, Integer> durations,
                                          int howManyWeeks, LocalDate startingDate){
        boolean worked = calendar.addPeriodicAppointment(student, title, price, timeInfo,
                durations, howManyWeeks, startingDate);
        if (worked){
            saveCalendar();
        }
        return worked;
    }

    // deletes appointment
    public void deleteAppointment(Appointment appointment){
        boolean worked = calendar.deleteAppointment(appointment);
        if (worked){
            saveCalendar();
        }
    }

    // set path
    public static void setPath(File path){
        Teacher.path = path;
    }

    // save calendar
    public void saveCalendar(){
        File calendarFile = new File(path, CALENDAR_FILENAME);
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(calendarFile));
            stream.writeObject(calendar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // save students
    public void saveStudents(){
        File studentsFile = new File(path, STUDENTS_FILENAME);
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(studentsFile));
            stream.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // save assignments
    public void saveAssignments(){
        File assignmentsFile = new File(path, ASSIGNMENTS_FILENAME);
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(assignmentsFile));
            stream.writeObject(assignments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////
    //                    getters                      //
    /////////////////////////////////////////////////////

    // get singleton teacher
    public static Teacher getTeacher(){
        if (teacher == null){
            teacher = new Teacher();
        }
        return teacher;
    }

    // appointments from calendar
    public ArrayList<Appointment> getAllAppointments(){
        return calendar.getAppointments();
    }
    public ArrayList<Appointment> getFirstFutureAppointments(int number){
        ArrayList<Appointment> toReturn = new ArrayList<>();
        ArrayList<Appointment> appointments = calendar.getAppointments();
        LocalDateTime today = LocalDateTime.now();
        int counter = 0;
        for (Appointment a: appointments){
            if (a.getDateStart().isAfter(today)){
                toReturn.add(a);
            }
            counter++;
            if (counter == number){
                break;
            }
        }
        return toReturn;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    /////////////////////////////////////////////////////
    //                     static                      //
    /////////////////////////////////////////////////////

    // how may weeks
    public final static int MIN_HOW_MANY_WEEKS = 1;
    public final static int MAX_HOW_MANY_WEEKS = 52;

    // duration time in minutes
    public final static int MIN_DURATION = 0;
    public final static int MAX_DURATION = 1440;

    // price
    public final static int MIN_PRICE = 0;
    public final static int MAX_PRICE = 999;

    // file names
    public final String ASSIGNMENTS_FILENAME = "appointments";
    public final String STUDENTS_FILENAME = "students";
    public final String CALENDAR_FILENAME = "calendar";
}
