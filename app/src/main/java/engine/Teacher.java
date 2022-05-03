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

    // assignments list
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
            // read all classes
            File teacherFile = new File(path, ALL_CLASSES_FILENAME);
            try {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(teacherFile));
                this.calendar = (Calendar) stream.readObject();
                this.students = (ArrayList<Student>) stream.readObject();
                this.assignments = (ArrayList<Assignment>) stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                this.assignments = new ArrayList<>();
                this.students = new ArrayList<>();
                this.calendar = new Calendar();
            }
        }else{
            // initialize empty
            this.calendar = new Calendar();
            this.students = new ArrayList<>();
            this.assignments = new ArrayList<>();
        }
    }

    // add student to students list with students values
    public void addStudent(String firstName, String surName, String email, String phone){
        Student student = new Student(firstName, surName, email, phone);
        students.add(student);
        save();
    }

    //add student to students list with student object
    public void addStudent(Student student){
        students.add(student);
        save();
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
        save();
    }

    // create assignments with attachments
    public void createAssignment(String name, String description, String content ,ArrayList<String> attachmentsFileNames){
        Assignment assignment = new Assignment(name, description, content ,attachmentsFileNames);
        assignments.add(assignment);
        save();
    }

    // create assignments without attachments
    public void createAssignment(String name, String description, String content){
        Assignment assignment = new Assignment(name, description, content);
        assignments.add(assignment);
        save();
    }

    // create assignments with object
    public void createAssignment(Assignment assignment){
        assignments.add(assignment);
        save();
    }

    //delete assignments from list
    public void deleteAssignment(Assignment assignmentToDelete){
        for(Assignment toDelete : assignments){
            if(toDelete.equals(assignmentToDelete)){
                assignments.remove(toDelete);
                break;
            }
        }
        save();
    }

    // add single appointment
    public boolean addSingleAppointment(Student student, String title, double price,
                                        LocalDateTime dateStart, int duration){
        boolean worked = calendar.addSingleAppointment(student, title, price, dateStart, duration);
        if (worked){
            save();
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
            save();
        }
        return worked;
    }

    // deletes appointment
    public void deleteAppointment(Appointment appointment){
        boolean worked = calendar.deleteAppointment(appointment);
        if (worked){
            save();
        }
    }

    // set path
    public static void setPath(File path){
        Teacher.path = path;
    }

    // TODO: 2. Complete save methode. Use ALL_CLASSES_FILENAME variable as file name.
    //  Pay attention to the load order in constructor.
    public void save(){

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
       return calendar.getFirstFutureAppointments(number);
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

    public final String ALL_CLASSES_FILENAME = "teacher";
}
