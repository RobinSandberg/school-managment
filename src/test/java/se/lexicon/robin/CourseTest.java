package se.lexicon.robin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CourseTest {

    Course course;
    Student student;

    @Before
    public void setUp(){
        course = new Course("Java", LocalDate.parse("2020-10-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")) , 12);
        student = new Student("Robin", "Robin@mail.com", "Skansvägen");
    }

    @After
    public void tearDown(){
        course.reset();
        student.reset();
    }

    @Test
    public void getIdSuccessfully(){
        Assert.assertEquals(1,course.getId());
    }

    @Test
    public void getNameSuccessfully(){
        Assert.assertEquals("Java", course.getCourseName());
    }

    @Test
    public void setNameSuccessfully(){
        course.setCourseName("Java pro");
        Assert.assertEquals("Java pro", course.getCourseName());
    }

    @Test
    public void getStartDateSuccessfully(){
        LocalDate date = LocalDate.parse("2020-10-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Assert.assertEquals(date, course.getStartDate());
    }

    @Test
    public void setStartDateSuccessfully(){
        LocalDate date = LocalDate.parse("2022-03-12", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        course.setStartDate(date);

        Assert.assertEquals(date, course.getStartDate());
    }

    @Test
    public void getWeekDurationSuccessfully(){
        Assert.assertEquals(12,course.getWeekDuration());
    }

    @Test
    public void setWeekDurationSuccessfully(){
        course.setWeekDuration(21);
        Assert.assertEquals(21, course.getWeekDuration());
    }

    @Test
    public void registerStudentSuccessfully(){
        course.register(student);

        Assert.assertEquals("Robin",  course.getStudents().get(0).getName());
        Assert.assertNotNull(course.getStudents());
    }

    @Test
    public void unregisterStudentSuccessfully(){
        course.register(student);
        System.out.println(course.getStudents().get(0).getName());
        course.unregister(student);
        Assert.assertEquals(0 , course.getStudents().size());

    }

    @Test
    public void toStringSuccessfully(){
        String toString = course.toString();
        String weekDuration =  ""+course.getWeekDuration();

        Assert.assertTrue(toString.contains(course.getCourseName()));
        Assert.assertTrue(toString.contains(course.getStartDate().toString()));
        Assert.assertTrue(toString.contains(weekDuration));
    }
}
