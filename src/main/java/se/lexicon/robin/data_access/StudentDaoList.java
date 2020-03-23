package se.lexicon.robin.data_access;

import se.lexicon.robin.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoList implements StudentDao {

    private static List<Student> students;

    public StudentDaoList(){
        students = new ArrayList<>();
    }

    @Override
    public Student saveStudent(Student student){
        if (!students.contains(student)){
            students.add(student);
            return student;
        }
        return null;
    }

    @Override
    public Student findByEmail(String email){
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return student;
            }
        }
        System.out.println("Student email don't exist.");
        return null;
    }

    @Override
    public List<Student> findByName(String name){
       List<Student> foundByNameStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                foundByNameStudents.add(student);
            }
        }
       return foundByNameStudents;
    }

    @Override
    public Student findById(int id){
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        System.out.println("Student id don't exist.");
        return null;
    }

    @Override
    public List<Student>findAll(){
        return students;
    }

    @Override
    public boolean deleteStudent(Student student){
        return students.remove(student);
    }
}
