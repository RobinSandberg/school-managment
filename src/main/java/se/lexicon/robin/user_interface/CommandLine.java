package se.lexicon.robin.user_interface;

import se.lexicon.robin.Course;
import se.lexicon.robin.Student;

import java.time.LocalDate;
import java.util.Scanner;

public interface CommandLine {

    Student createNewStudent();
    Course createNewCourse();
    Student editStudentName(Student student);
    Student editStudentAddress(Student student);
    Student editStudentEmail(Student student);
    Course editCourseName(Course course);
    Course editCourseStartDate(Course course);
    Course editCourseDuration(Course course);
    boolean registerStudent(Course course,Student student);
    boolean unregisterStudent(Course course,Student student);

}
