package se.lexicon.robin;

import java.time.LocalDate;
import java.util.*;

public class Course {
    private int id;
    private static int counter = 0;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private List<Student> students;

    public Course(){
        students = new ArrayList<>();
    }

    public int getId(){
        return  this.id;
    }

    public void setId(){
        counter++;
        this.id = counter;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public LocalDate getStartDate(){
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public int getWeekDuration(){
        return this.weekDuration;
    }

    public void setWeekDuration(int weekDuration){
        this.weekDuration = weekDuration;
    }

    public List<Student> getStudents(){
        return this.students;
    }

    public void register(Student student){
        this.students.add(student);
    }

    public void unregister(Student student){
        this.students.remove(student);
    }
}