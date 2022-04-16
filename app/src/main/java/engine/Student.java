package engine;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    //description
    private String description;

    //when added
    private final LocalDateTime added;

    //list of assigned assignments
    private ArrayList<AssignedAssignment> assignments;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    //constructor
    public Student(String firstName, String surName, String email, String phone) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
        this.added = LocalDateTime.now();
        this.description = "";
        this.assignments = new ArrayList<>();
    }

    //add new assignedAssignment from list of existing Assignments
    public void addAssignedAssignmentFromList(Assignment assignmentToAdd){
        AssignedAssignment newAssignment = new AssignedAssignment(assignmentToAdd.getAssignmentName(),
                assignmentToAdd.getDescription(), assignmentToAdd.getContent(),
                assignmentToAdd.getAttachmentsFileNames());
        assignments.add(newAssignment);
    }

    //adding directly new assignedAssignment
    public void addAssignedAssignmentDirectly(String assignmentName, String description, String content){
        AssignedAssignment newAssignment = new AssignedAssignment(assignmentName, description, content);
        assignments.add(newAssignment);
    }

    //delete assigned assignment from a list of assignments assigned to the particular student
    public void deleteAssignment(AssignedAssignment aassignmentToDelete){
        //checking if it contains same object
        if(assignments.contains(aassignmentToDelete)) {
            assignments.remove(aassignmentToDelete);
        //checking by values of objects
        }else{
            for(AssignedAssignment toDelete : assignments){
                if(toDelete.equals(aassignmentToDelete)){
                    assignments.remove(toDelete);
                    break;
                }
            }
        }
    }

    //delete assignment from a list of assignments assigned to the particular student
    public void deleteAssignment(Assignment assignmentToDelete){
        //checking if it contains same object
        if(assignments.contains(assignmentToDelete)) {
            assignments.remove(assignmentToDelete);
        //checking by values of objects
        }else{
            for(AssignedAssignment toDelete : assignments){
                if(toDelete.equals(assignmentToDelete)){
                    assignments.remove(toDelete);
                    break;
                }
            }
        }
    }

    //edit student
    public void editStudent(String firstName, String surName, String email, String phone,String description){
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    // to string
    @NotNull
    @Override
    public String toString(){
        return this.firstName + " " + this.surName;
    }

    /////////////////////////////////////////////////////
    //                    getters                      //
    /////////////////////////////////////////////////////

    public String getFirstName() {return firstName;}

    public String getSurName() {return surName;}

    public String getEmail() {return email;}

    public String getPhone() {return phone;}

    public String getMarked() {return String.valueOf(added);}

    public String getDescription() {return description;}

    public ArrayList<AssignedAssignment> getAssignments() {return assignments;}
}
