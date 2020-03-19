package se.lexicon.robin.data_access;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.lexicon.robin.Student;

import java.util.List;

public class StudentDaoListTest {

    StudentDaoList studentDaoList;
    Student student;
    Student student1;

    @Before
    public void setUp(){
        studentDaoList = new StudentDaoList();
        student = new Student("Robin", "Robin@mail.com","Skansvägen");
        student1 = new Student("Bengt", "Bengt@mail.com","Kråkvägen");
        studentDaoList.saveStudent(student);
        studentDaoList.saveStudent(student1);
    }

    @After
    public void tearDown(){
        student.reset();
    }

    @Test
    public void saveStudentSuccessfully(){
        student = new Student("Martin", "Martin@mail.com","SniggelGatan");
        studentDaoList.saveStudent(student);

        Assert.assertEquals(3, studentDaoList.findAll().size());
        Assert.assertEquals("Martin", studentDaoList.findAll().get(2).getName());
    }

    @Test
    public void saveStudentUnsuccessfully(){
        studentDaoList.saveStudent(student);

        Assert.assertEquals(2, studentDaoList.findAll().size());
    }

    @Test
    public void findByEmailSuccessfully(){
        Student foundStudent = studentDaoList.findByEmail("robin@mail.com");

        Assert.assertEquals("Robin", foundStudent.getName());
    }

    @Test
    public void findByEmailUnsuccessfully(){
        Student foundStudent = studentDaoList.findByEmail("Martin@mail.com");

        Assert.assertNull(foundStudent);
    }

    @Test
    public void findByNameSuccessfully(){
        List<Student> foundStudents = studentDaoList.findByName("robin");

        Assert.assertEquals(1, foundStudents.size());
        Assert.assertEquals("Robin", foundStudents.get(0).getName());
    }

    @Test
    public void findByIdSuccessfully(){
        Student foundStudent = studentDaoList.findById(2);

        Assert.assertEquals(student1, foundStudent);
    }

    @Test
    public void findByIdUnsuccessfully(){
        Student foundStudent = studentDaoList.findById(3);

        Assert.assertNull(foundStudent);
    }

    @Test
    public void findAllSuccessfully(){
        List<Student> foundStudents = studentDaoList.findAll();

        Assert.assertEquals(2, foundStudents.size());
        Assert.assertEquals("Robin", foundStudents.get(0).getName());
        Assert.assertEquals("Bengt", foundStudents.get(1).getName());
    }

    @Test
    public void deleteStudentSuccessfully(){
        studentDaoList.deleteStudent(student);

        Assert.assertEquals(1, studentDaoList.findAll().size());
        Assert.assertEquals("Bengt", studentDaoList.findAll().get(0).getName());
    }
}
