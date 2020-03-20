package se.lexicon.robin.user_interface;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.lexicon.robin.Course;
import se.lexicon.robin.Student;
import se.lexicon.robin.data_access.CourseDaoList;
import se.lexicon.robin.data_access.StudentDaoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommandLineFunctionsTest {

    Student student;
    Student student1;
    Course course;
    CommandLineFunctions commandLineFunctions;
    CourseDaoList courseDaoList;
    StudentDaoList studentDaoList;

    @Before
    public void setUp(){
        commandLineFunctions = new CommandLineFunctions();
        courseDaoList = new CourseDaoList();
        studentDaoList = new StudentDaoList();
        student = new Student("Robin", "robin@mail.com" ,"skansvägen" );
        student1 = new Student("Bengt", "bengt@mail.com" ,"kråkvägen" );
        course = new Course("Java", LocalDate.parse("2020-10-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")) , 12 );
        courseDaoList.saveCourse(course);
        studentDaoList.saveStudent(student);
        studentDaoList.saveStudent(student1);
        commandLineFunctions.registerStudent(course, student);
    }

    @After
    public void tearDown(){
        student.reset();
        course.reset();
    }

    @Test
    public void registerStudentSuccessfully(){
        commandLineFunctions.registerStudent(course,student1);

        Assert.assertEquals(2, commandLineFunctions.courseDaoList.findAll().get(0).getStudents().size());
        Assert.assertEquals("Bengt", commandLineFunctions.courseDaoList.findAll().get(0).getStudents().get(1).getName());
    }
    @Test
    public void registerStudentUnsuccessfully(){
        commandLineFunctions.registerStudent(course,student);

        Assert.assertEquals(1, commandLineFunctions.courseDaoList.findAll().get(0).getStudents().size());
        Assert.assertEquals("Robin", commandLineFunctions.courseDaoList.findAll().get(0).getStudents().get(0).getName());
    }

    @Test
    public void unregisterStudentSuccessfully(){
        commandLineFunctions.unregisterStudent(course, student);

        Assert.assertEquals(0, commandLineFunctions.courseDaoList.findAll().get(0).getStudents().size());
    }

    @Test
    public void unregisterStudentUnsuccessfully(){
        commandLineFunctions.unregisterStudent(course, student1);

        Assert.assertEquals(1, commandLineFunctions.courseDaoList.findAll().get(0).getStudents().size());
    }

}
