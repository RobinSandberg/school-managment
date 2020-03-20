package se.lexicon.robin;

import org.junit.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentTest {
         Student student;
         Course course;
         Course course1;

        @Before
        public void setUp(){
            student = new Student("Robin", "robin@mail.com" ,"skansvägen" );
            course = new Course("Java", LocalDate.parse("2020-10-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")) , 12 );
            course1 = new Course(".Net", LocalDate.parse("2020-05-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")) , 21 );
            student.addCourse(course);
        }

        @After
        public void tearDown(){
            student.reset();
            course.reset();
        }

        @Test
        public void getIdSuccessfully() {
            Assert.assertEquals(1,student.getId());
        }

        @Test
        public void getNameSuccessfully() {
            Assert.assertEquals("Robin", student.getName());
        }

        @Test
        public void setNameSuccessfully() {
            student.setName("Bengt");
            Assert.assertEquals("Bengt", student.getName());
        }

        @Test
        public void getEmailSuccessfully() {
            Assert.assertEquals("robin@mail.com", student.getEmail());
        }

        @Test
        public void setEmailSuccessfully() {
            student.setEmail("Bengt@mail.com");
            Assert.assertEquals("Bengt@mail.com", student.getEmail());
        }

        @Test
        public void getAddressSuccessfully() {
            Assert.assertEquals("skansvägen", student.getAddress());
        }

        @Test
        public void setAddressSuccessfully() {
            student.setAddress("Roadhouse");
            Assert.assertEquals("Roadhouse", student.getAddress());
        }

        @Test
        public void getCourseListSuccessfully(){
            List<Course> foundCourses = student.getCourseList();

            Assert.assertEquals(1, foundCourses.size());
            Assert.assertEquals("Java", foundCourses.get(0).getCourseName());
        }

        @Test
        public void addCourseSuccessfully(){
            student.addCourse(course1);

            Assert.assertEquals(2, student.getCourseList().size());
            Assert.assertEquals(".Net", student.getCourseList().get(1).getCourseName());
        }

        @Test
        public void removeCourseSuccessfully(){
            student.removeCourse(course);

            Assert.assertEquals(0 , student.getCourseList().size());
        }

        @Test
        public void toStringSuccessfully(){
            String toString = student.toString();

            Assert.assertTrue(toString.contains(student.getName()));
            Assert.assertTrue(toString.contains(student.getEmail()));
            Assert.assertTrue(toString.contains(student.getAddress()));
        }
}
