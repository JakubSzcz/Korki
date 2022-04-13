package engine;

import java.util.ArrayList;

public class Homework{
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    //name of homework
    private String homeworkName;

    //short description
    private String description;

    //list of files names attached to the homework
    private ArrayList<String> attachmentsFileNames;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //constructor
    public Homework(String homeworkName, String description, ArrayList<String> attachmentsFileNames ) {
        this.homeworkName = homeworkName;
        this.description = description;
        this.attachmentsFileNames = attachmentsFileNames;
    }

    //getters

    public String get_homeworkName() {return homeworkName;}

    public String get_description() {return description;}

    public ArrayList<String> get_attachmentsFileNames() {return attachmentsFileNames;}
}
