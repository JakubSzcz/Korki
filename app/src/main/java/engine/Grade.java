package engine;

import java.time.LocalDateTime;

public class Grade {
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    //what is your mark
    private String mark;

    //homework's name
    private String homeworkName;

    //when homework was marked
    //private final LocalDateTime marked;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //constructor
    public Grade(String mark, String homeworkName) {
        this.mark = mark;
        this.homeworkName = homeworkName;
    }

    public String get_mark() {return mark;}

    public String get_homeworkName() {return homeworkName;}
}
