package engine;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Assignment implements Serializable {
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    //name of assignments
    private String assignmentName;

    //short description
    private String description;

    //content of assignments
    private String content;

    //list of files names attached to the assignments
    private ArrayList<String> attachmentsFileNames;

    //when assignments was created
    private LocalDateTime added;
    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //constructor with attachments
    public Assignment(String assignmentName, String description, String content,
                      ArrayList<String> attachmentsFileNames ) {
        this.assignmentName = assignmentName;
        this.description = description;
        this.attachmentsFileNames = attachmentsFileNames;
        this.content = content;
        this.added = LocalDateTime.now();
    }
    //constructor without attachments
    public Assignment(String assignmentName, String description, String content) {
        this.assignmentName = assignmentName;
        this.description = description;
        this.content = content;
        this.added = LocalDateTime.now();
    }

    //check if both assignments are equal
    public boolean equals(Assignment assignment){
        if (this.assignmentName != assignment.getAssignmentName() ){
            return false;
        }else if (!this.description.equals(assignment.getDescription())){
            return false;
        }else if(!this.content.equals(assignment.getContent())){
            return false;
        }else{
            return true;
        }
    }

    //check if both assigned assignments are equal
    //chyba mozna zrobic lepiej, typu ze bede tworzyc obiekt assignments i wywoływać na nim te funkcje z gory, zeby nie
    //bylo dwoch praktycznie identycznych funkcji
    public boolean equals(AssignedAssignment aassignment){
        if (this.assignmentName != aassignment.getAssignmentName() ){
            return false;
        }else if (!this.description.equals(aassignment.getDescription())){
            return false;
        }else if(!this.content.equals(aassignment.getContent())){
            return false;
        }else{
            return true;
        }
    }
    /////////////////////////////////////////////////////
    //                    getters                      //
    /////////////////////////////////////////////////////
    public String getAssignmentName() {return assignmentName;}

    public String getDescription() {return description;}

    public ArrayList<String> getAttachmentsFileNames() {return attachmentsFileNames;}

    public String getContent() {return content;}

    public LocalDateTime getAdded() {return added;}
}
