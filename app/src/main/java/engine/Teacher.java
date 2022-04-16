package engine;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    // singleton teacher
    private static Teacher teacher;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    // constructor
    private Teacher(){
        // TODO: load or initialize variables
        ////////// tests purposes
        this.calendar = new Calendar();
        this.students = new ArrayList<>();
        addStudent("Jan", "Kowalski", "C", "D");
        for (int i = 0; i < 10; i++){
            calendar.addSingleAppointment(students.get(0), "Meeting: " + i, 50,
                    LocalDateTime.now().plusDays(i), 90);
        }
        //////////
    }

    // add student to students list
    public void addStudent(String firstName, String surName, String email, String phone){
        Student student = new Student(firstName, surName, email, phone);
        students.add(student);
    }

    // delete student from students list
    public void deleteStudent(){
        // TODO
    }

    // create assignment with attachments
    public void createAssignment(String name, String description, String content ,ArrayList<String> attachmentsFileNames){
        Assignment assignment = new Assignment(name, description, content ,attachmentsFileNames);
        assignments.add(assignment);
    }

    // create assignment without attachments
    public void createAssignment(String name, String description, String content){
        Assignment assignment = new Assignment(name, description, content);
        assignments.add(assignment);
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

    // get singleton teacher
    public static Teacher getTeacher(){
        if (teacher == null){
            teacher = new Teacher();
        }
        return teacher;
    }

    /////////////////////////////////////////////////////
    //                    getters                      //
    /////////////////////////////////////////////////////

    public Calendar getCalendar() {
        return calendar;
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
}
