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
       courses.add(course);
       return course;
    }

    @Override
    public Course findById(int id){
      for(int i = 0; i < courses.size(); i++){
          if(courses.get(i).getId() == id){
              return courses.get(i);
          }
      }
        System.out.println("Course id don't exist.");
      return null;
    }

    @Override
    public List<Course> findByName(String name){
        List<Course> foundByNameCourses = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getCourseName().toLowerCase().contains(name.toLowerCase())){
                foundByNameCourses.add(courses.get(i));
            }
        }
        return foundByNameCourses;
    }

    @Override
    public List<Course> findByDate(LocalDate date){
        List<Course> foundByDateCourses = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getStartDate().equals(date)){
                foundByDateCourses.add(courses.get(i));
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
