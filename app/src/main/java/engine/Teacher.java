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
    }

    // add student to students list
    public void addStudent(String firstName, String surName, String email, String phone){
        // TODO: student constructor
        Student student = new Student(firstName, surName, email, phone);
        students.add(student);
    }

    // delete student from students list
    public void deleteStudent(){
        // TODO
    }

    // add appointment to calendar
    public void addAppointment(String title, LocalDateTime dateStart, int duration, float price){
        // TODO: action after worked and not worked
        // boolean worked = calendar.addAppointment(title, dateStart, duration, price);
    }

    // delete appointment from calendar
    public void deleteAppointment(){
        // TODO
    }

    // create assignment
    public void createAssignment(String name, String description, String content ,ArrayList<String> attachmentsFileNames){
        // TODO: Homework constructor
        Assignment assignment = new Assignment(name, description, content ,attachmentsFileNames);
        assignments.add(assignment);
    }

    //
    public void editAssignment(){
        // TODO
    }

    //
    public void deleteAssignment(){
        // TODO
    }

    // get singleton teacher
    public static Teacher getTeacher(){
        if (teacher == null){
            teacher = new Teacher();
        }
        return teacher;
    }
}
