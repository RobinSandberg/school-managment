package se.lexicon.robin.data_access;

import se.lexicon.robin.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoList implements CourseDao {

    private static List<Course> courses;

    public CourseDaoList(){
        courses = new ArrayList<>();
    }

    @Override
    public Course saveCourse(Course course){
        if (!courses.contains(course)) {
            courses.add(course);
            return course;
        }
        return null;
    }

    @Override
    public Course findById(int id){
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        System.out.println("Course id don't exist.");
      return null;
    }

    @Override
    public List<Course> findByName(String name){
        List<Course> foundByNameCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCourseName().toLowerCase().contains(name.toLowerCase())) {
                foundByNameCourses.add(course);
            }
        }
        return foundByNameCourses;
    }

    @Override
    public List<Course> findByDate(LocalDate date){
        List<Course> foundByDateCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getStartDate().equals(date)) {
                foundByDateCourses.add(course);
            }
        }
        return foundByDateCourses;
    }

    @Override
    public List<Course> findAll(){
        return courses;
    }

    @Override
    public boolean removeCourse(Course course){
        return courses.remove(course);
    }
}
