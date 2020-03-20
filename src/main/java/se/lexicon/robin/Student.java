package se.lexicon.robin;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final int id;
    private static int counter = 0;
    private String name;
    private String email;
    private String address;

    List<Course> courseList;

    public Student(String name, String email , String address){
        this.name = name;
        this.email = email;
        this.address = address;
        this.id = ++counter;
        courseList = new ArrayList<>();
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void reset(){
        counter = 0;
    }

    public List<Course> getCourseList(){
        return this.courseList;
    }

    public void addCourse(Course course){
        this.courseList.add(course);
    }

    public void removeCourse(Course course){
        this.courseList.remove(course);
    }

    @Override
    public String toString() {
        return "\nStudent id = " + id +
                "\nStudent name = " + name +
                "\nStudent email = " + email +
                "\nStudent address = " + address;
    }
}
