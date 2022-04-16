package engine;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentTest {

    //testing adding assignment function from list of existing assignments
    @Test
    public void addAssignedAssignmentFromList() {
        //student
        Student student = new Student("A", "C", "C", "D");
        //assignment without attachments from the list
        Assignment assignment = new Assignment("a","b","c");
        //aasignment without attachments
        AssignedAssignment assignedAssignment = new AssignedAssignment("a","b","c");

        //adding assignment from list
        student.addAssignedAssignmentFromList(assignment);
        //adding assignment directly
        student.addAssignedAssignmentDirectly("a","b","c");

        assertTrue(student.getAssignments().get(0).equals(assignedAssignment));
        assertTrue(student.getAssignments().get(1).equals(assignedAssignment));

    }

    @Test
    public void deleteAssignment() {
        //student
        Student student = new Student("A", "C", "C", "D");
        //assignment 1 without attachments from the list
        Assignment assignment = new Assignment("a","b","c");
        //assignment 1 without attachments from the list
        Assignment assignment2 = new Assignment("d","e","f");
        //aasignment without attachments
        AssignedAssignment assignedAssignment = new AssignedAssignment("a","b","c");
        //aasignment2 without attachments
        AssignedAssignment assignedAssignment2 = new AssignedAssignment("d","e","f");
        //adding assignments to the list
        student.addAssignedAssignmentFromList(assignment);
        student.addAssignedAssignmentFromList(assignment2);

        //deleting assignment2
        student.deleteAssignment(assignedAssignment);
        assertTrue(student.getAssignments().size()==1); //size decreased
        assertTrue(student.getAssignments().get(0).equals(assignedAssignment2)); //good element was removed
    }

    @Test
    public void editStudent() {
        Student student = new Student("A", "C", "C", "D");
        student.editStudent("E","F","G","H","");
        assertTrue(student.getFirstName().equals("E"));
        assertTrue(student.getSurName().equals("F"));
        assertTrue(student.getEmail().equals("G"));
        assertTrue(student.getPhone().equals("H"));
    }
}