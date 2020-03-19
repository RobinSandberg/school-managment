package se.lexicon.robin.data_access;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.lexicon.robin.Course;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CourseDaoListTest {

    CourseDaoList courseDaoList;
    Course course;
    Course course1;

    @Before
    public void setUp(){
        courseDaoList = new CourseDaoList();
        course = new Course("Java", LocalDate.parse("2020-10-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")) , 12);
        course1 = new Course(".Net", LocalDate.parse("2021-05-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")) , 8);
        courseDaoList.saveCourse(course);
        courseDaoList.saveCourse(course1);
    }

    @After
    public void tearDown(){
        course.reset();
    }


    @Test
    public void saveCourseSuccessfully(){
        course = new Course("Rails", LocalDate.parse("2020-01-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")) , 20);
        courseDaoList.saveCourse(course);
        Assert.assertEquals(3, courseDaoList.findAll().size());
        Assert.assertEquals("Rails", courseDaoList.findAll().get(2).getCourseName());
    }

    @Test
    public void saveCourseUnsuccessfully(){
        courseDaoList.saveCourse(course);
        Assert.assertEquals(2, courseDaoList.findAll().size());
    }

    @Test
    public void findByIdSuccessfully(){
        Course foundCourse = courseDaoList.findById(2);

        Assert.assertEquals(course1, foundCourse);
    }

    @Test
    public void findByIdUnsuccessfully(){
        Course foundCourse = courseDaoList.findById(3);

        Assert.assertNull(foundCourse);
    }

    @Test
    public void findByNameSuccessfully(){
        List<Course> foundCourses = courseDaoList.findByName("java");

        Assert.assertEquals(1,foundCourses.size());
        Assert.assertEquals("Java", foundCourses.get(0).getCourseName());
    }

    @Test
    public void findByDateSuccessfully(){
        List<Course> foundCourses =  courseDaoList.findByDate(LocalDate.parse("2020-10-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        Assert.assertEquals(1, foundCourses.size());
        Assert.assertEquals("Java", foundCourses.get(0).getCourseName());
    }

    @Test
    public void findAllSuccessfully(){
        List<Course> foundCourses = courseDaoList.findAll();

        Assert.assertEquals(2, foundCourses.size());
        Assert.assertEquals("Java", foundCourses.get(0).getCourseName());
        Assert.assertEquals(".Net", foundCourses.get(1).getCourseName());
    }

    @Test
    public void removeCourseSuccessfully(){
        courseDaoList.removeCourse(course);

        Assert.assertEquals(1, courseDaoList.findAll().size());
        Assert.assertEquals(".Net", courseDaoList.findAll().get(0).getCourseName());
    }

}
