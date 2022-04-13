package engine;

import java.time.LocalDateTime;  //--> inny format trzeba dac bo android 7.0 nie obsluguje
import java.util.ArrayList;

public class Teacher {
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    // list of students
    private ArrayList<Student> students;

    // calendar object which contains all appointments
    private Calendar calendar;

    // homeworks list
    private ArrayList<Homework> homeworks;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    // constructor
    public Teacher(){
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
        boolean worked = calendar.addAppointment(title, dateStart, duration, price);
    }

    // delete appointment from calendar
    public void deleteAppointment(){
        // TODO
    }

    // create homework
    public void createHomework(String name, String description, ArrayList<String> attachmentsFileNames){
        // TODO: Homework constructor
        Homework homework = new Homework(name, description, attachmentsFileNames);
        homeworks.add(homework);
    }

    //
    public void editHomework(){
        // TODO
    }

    //
    public void deleteHomework(){
        // TODO
    }
}
