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
    private final LocalDateTime added;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //construcotr

    public Student(String firstName, String surName, String email, String phone) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
        this.added = LocalDateTime.now();
    }


    //getters:
    public String getFirstName() {return firstName;}

    public String getSurName() {return surName;}

    public String getEmail() {return email;}

    public String getPhone() {return phone;}

    public String getMarked() {return String.valueOf(added);}
}
