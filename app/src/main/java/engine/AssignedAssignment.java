package engine;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AssignedAssignment extends Assignment{
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    //evaluation of assignment
    private String mark;

    //sent time
    private LocalDateTime sentTime;

    //was task completed
    private boolean isCompleted;
    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //constructor
    public AssignedAssignment(String assignmentName, String description, String content, ArrayList<String> attachmentsFileNames, String mark, boolean isCompleted) {
        super(assignmentName, description, content, attachmentsFileNames);
        this.mark = mark;
        this.isCompleted = isCompleted;
    }
}
