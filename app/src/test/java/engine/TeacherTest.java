package engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeacherTest {

    @Test
    public void deleteAssignment() {

    }

    @Test
    public void deleteStudent() {

        //not included student
        Student student1 = new Student("A", "B", "C", "D");
        //included student
        Student student2 = new Student("Jan", "Kowalski", "jan.kowalski@example.com", "999888777");
        //delete non existing student
        Teacher.getTeacher().deleteStudent(student1);
        assertTrue(Teacher.getTeacher().getStudents().size()==3);
        //delete existing student
        Teacher.getTeacher().deleteStudent(student2);
        assertTrue(Teacher.getTeacher().getStudents().size() == 2);
    }
}