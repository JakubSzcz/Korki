package engine;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Assignment {
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    //name of assignment
    private String assignmentName;

    //short description
    private String description;

    //content of assignment
    private String content;

    //list of files names attached to the assignment
    private ArrayList<String> attachmentsFileNames;

    //when assignment was created
    private LocalDateTime added;
    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //constructor
    public Assignment(String assignmentName, String description, String content ,ArrayList<String> attachmentsFileNames ) {
        this.assignmentName = assignmentName;
        this.description = description;
        this.attachmentsFileNames = attachmentsFileNames;
        this.content = content;
        this.added = LocalDateTime.now();
    }

    //getters
    public String getAssignmentName() {return assignmentName;}

    public String getDescription() {return description;}

    public ArrayList<String> getAttachmentsFileNames() {return attachmentsFileNames;}
}
