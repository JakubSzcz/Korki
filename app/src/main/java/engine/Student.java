package engine;

import java.time.LocalDateTime;

public class Student {
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    //first name
    private String firstName;

    //surname
    private String surName;

    //email
    private String email;

    //phone number
    private String phone;

    //when added
    //private LocalDateTime added;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //construcotr

    public Student(String firstName, String surName, String email, String phone) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
        //this.added = LocalDateTime.now(); --> inny format trzeba dac bo android 7.0 nie obsluguje
    }

    //getters:
    public String get_firstName() {return firstName;}

    public String get_surName() {return surName;}

    public String get_email() {return email;}

    public String get_phone() {return phone;}
}
