package se.lexicon.robin.user_interface;

import se.lexicon.robin.Course;
import se.lexicon.robin.Student;
import se.lexicon.robin.data_access.CourseDaoList;
import se.lexicon.robin.data_access.StudentDaoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandLineFunctions implements CommandLine{
    StudentDaoList studentDaoList = new StudentDaoList();
    CourseDaoList courseDaoList = new CourseDaoList();
    boolean attempt;
    @Override
    public Student createNewStudent(){
        attempt = true;
        String name = "";
        String address = "";
        String email = "";
        while(attempt) {
            System.out.println("What is the student name.");
            name = getStringFromUser();
            attempt = name.isEmpty();
        }
        attempt = true;
        while(attempt) {
            System.out.println("What is the student address.");
            address =  getStringFromUser();
            attempt = address.isEmpty();
        }
        attempt = true;
        while(attempt) {
            System.out.println("What is the student email.");
            email = getStringFromUser();
            attempt = email.isEmpty();
        }
        return new Student(name , email , address);
    }

    @Override
    public Course createNewCourse(){
        attempt = true;
        String courseName = "";
        LocalDate startDate = LocalDate.parse("0001-01-01" , DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int weekDuration = 0;
        while (attempt){
            System.out.println("What is the course name.");
            courseName = getStringFromUser();
            attempt = courseName.isEmpty();
        }
        boolean wrongFormatCheck = true;
        while(wrongFormatCheck) {
            System.out.println("What is the start date for the course Year-month-day.");
            try {
                startDate = LocalDate.parse(getStringFromUser(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                wrongFormatCheck = false;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format!");
            }
        }
        while(weekDuration <= 0) {
            System.out.println("How many weeks is the course.");
            weekDuration = getNumber();
        }
        return new Course(courseName , startDate, weekDuration);
    }

    @Override
    public Student editStudentName(Student student){
        Student student1 = studentDaoList.findById(student.getId());
        attempt = true;
        while(attempt) {
            System.out.println("Edit: " + student1.getName());
            student1.setName(getStringFromUser());
            attempt = student1.getName().isEmpty();
        }
        return student1;
    }

    @Override
    public Student editStudentAddress(Student student){
        Student student1 = studentDaoList.findById(student.getId());
        attempt = true;
        while (attempt) {
            System.out.println("Edit: " + student1.getAddress());
            student1.setAddress(getStringFromUser());
            attempt = student1.getAddress().isEmpty();
        }
        return student1;
    }

    @Override
    public Student editStudentEmail(Student student){
        Student student1 = studentDaoList.findById(student.getId());
        attempt = true;
        while(attempt) {
            System.out.println("Edit: " + student1.getEmail());
            student1.setEmail(getStringFromUser());
            attempt = student1.getEmail().isEmpty();
        }
        return student1;
    }

    @Override
    public Course editCourseName(Course course){
        Course course1 = courseDaoList.findById(course.getId());
        attempt = true;
        while (attempt) {
            System.out.println("Edit: " + course1.getCourseName());
            course1.setCourseName(getStringFromUser());
            attempt = course1.getCourseName().isEmpty();
        }
        return course1;
    }

    @Override
    public Course editCourseStartDate(Course course){
        Course course1 = courseDaoList.findById(course.getId());
        boolean wrongFormatCheck = true;
        while (wrongFormatCheck) {
            try {
                System.out.println("Edit: " + course1.getStartDate());
                course1.setStartDate(LocalDate.parse(getStringFromUser(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                wrongFormatCheck = false;
            }catch (DateTimeParseException e){
                System.out.println("Invalid date format!");
            }
        }
        return course1;
    }

    @Override
    public Course editCourseDuration(Course course){
        Course course1 = courseDaoList.findById(course.getId());
        attempt = true;
        while (attempt) {
            System.out.println("Edit: " + course1.getWeekDuration());
            course1.setWeekDuration(getNumber());
            attempt = course1.getWeekDuration() <= 0;
        }
        return course1;
    }

    @Override
    public boolean registerStudent(Course course, Student student){
        if(!course.getStudents().contains(student)){
            course.register(student);
            student.addCourse(course);
            return true;
        }
        System.out.println("Student already in this course.");
        return false;
    }

    @Override
    public boolean unregisterStudent(Course course,Student student){
        if(course.getStudents().contains(student)){
            course.unregister(student);
            student.removeCourse(course);
            return true;
        }
        System.out.println("Student was not in this course.");
        return false;
    }


    public String getStringFromUser(){
        return scanner.nextLine();
    }

    public int getNumber(){
        boolean valid = false;
        int number = 0;
        while(!valid){
            try{
                number = Integer.parseInt(getStringFromUser().trim());
                valid = true;
            }catch(NumberFormatException e){
                System.out.println("Input was not a number.");
            }
        }
        return number;
    }
}