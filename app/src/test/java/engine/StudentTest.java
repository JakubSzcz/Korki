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
    public void deleteAssignedAssignment() {
    }
}