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
    private final LocalDateTime marked;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //constructor
    public Grade(String mark, String homeworkName) {
        this.mark = mark;
        this.homeworkName = homeworkName;
        this.marked = LocalDateTime.now();
    }

    //getters

    public String getMark() {return mark;}

    public String getHomeworkName() {return homeworkName;}

    public String getMarked() {return String.valueOf(marked);}
}
