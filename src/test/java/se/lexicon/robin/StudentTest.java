package se.lexicon.robin;

import org.junit.*;

public class StudentTest {
         Student student;

        @Before
        public void setUp(){
            student = new Student("Robin", "robin@mail.com" ,"skansvägen" );
        }

        @After
        public void tearDown(){
            student.reset();
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
        public void toStringSuccessfully(){
            String toString = student.toString();

            Assert.assertTrue(toString.contains(student.getName()));
            Assert.assertTrue(toString.contains(student.getEmail()));
            Assert.assertTrue(toString.contains(student.getAddress()));
        }
}
