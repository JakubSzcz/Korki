package engine;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AssignedAssignment extends Assignment{
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    //evaluation of assignments
    private String mark;

    //sent time
    private LocalDateTime sentTime;

    //was task completed
    private boolean isCompleted;
    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //constructor with attachments
    public AssignedAssignment(String assignmentName, String description, String content,
                              ArrayList<String> attachmentsFileNames) {
        super(assignmentName, description, content, attachmentsFileNames);
        this.mark = "0%";
        this.isCompleted = false;
    }

    //constructor without attachments
    public AssignedAssignment(String assignmentName, String description, String content) {
        super(assignmentName, description, content);
        this.mark = "0%";
        this.isCompleted = false;
    }
}
