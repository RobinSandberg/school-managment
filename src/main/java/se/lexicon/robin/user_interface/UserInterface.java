package se.lexicon.robin.user_interface;

import se.lexicon.robin.Course;
import se.lexicon.robin.Student;
import se.lexicon.robin.data_access.CourseDaoList;
import se.lexicon.robin.data_access.StudentDaoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class UserInterface {

    CommandLineFunctions commandLineFunctions = new CommandLineFunctions();
    StudentDaoList studentDaoList = new  StudentDaoList();
    CourseDaoList courseDaoList = new CourseDaoList();

    public void menuOptions(){
        boolean running = true;
        while(running) {
            System.out.print("\nWelcome to the student/course manager." +
                    "\n1. Create a student." +
                    "\n2. Edit a student." +
                    "\n3. Remove a student." +
                    "\n4. Create a course." +
                    "\n5. Edit a course." +
                    "\n6. Remove a course." +
                    "\n7. Register student to a course." +
                    "\n8. Unregister student to a course." +
                    "\n9. Find student." +
                    "\n10. Find course." +
                    "\n11. Get students in a course." +
                    "\n12. Get courses a student goes." +
                    "\n13. Show all students." +
                    "\n14. Show all course." +
                    "\n15.Exit." +
                    "\n: INPUT: ");

            int input = commandLineFunctions.getNumber();

            switch (input) {
                case 1:
                    Student student = commandLineFunctions.createNewStudent();
                    student = studentDaoList.saveStudent(student);
                    System.out.println("Student added." +
                            student.toString());
                    break;
                case 2:
                    studentList();
                    System.out.println("What student you wanna edit type in the student Id:");
                    int studentId = commandLineFunctions.getNumber();
                    student = studentDaoList.findById(studentId);
                    if(student == null){
                        break;
                    }
                    System.out.print("\nWhat do you wanna edit?." +
                        "\n1. Student name." +
                        "\n2. Student address." +
                        "\n3. Student email." +
                        "\n4. Back to main menu." +
                        "\n: INPUT: ");
                    input = commandLineFunctions.getNumber();
                    switch(input){
                        case 1:
                            student = commandLineFunctions.editStudentName(student);
                            System.out.println(student.getName());
                            break;
                        case 2:
                            student = commandLineFunctions.editStudentAddress(student);
                            System.out.println(student.getAddress());
                            break;
                        case 3:
                            student = commandLineFunctions.editStudentEmail(student);
                            System.out.println(student.getEmail());
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Not a valid input!");
                            break;
                    }

                    break;
                case 3:
                    studentList();
                    System.out.println("What student you wanna remove type in the student Id:");
                    studentId = commandLineFunctions.getNumber();
                    student = studentDaoList.findById(studentId);
                    boolean removed = studentDaoList.deleteStudent(student);
                    System.out.println("Student removed: " + removed);
                    break;
                case 4:
                    Course course = commandLineFunctions.createNewCourse();
                    course = courseDaoList.saveCourse(course);
                    System.out.println("course added" +
                            course.toString());
                    break;
                case 5:
                    courseList();
                    System.out.println("What course you wanna edit type in the course Id:");
                    int courseId = commandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    if(course == null){
                        break;
                    }
                    System.out.print("\nWhat do you wanna edit?." +
                            "\n1. Course name." +
                            "\n2. Course start date." +
                            "\n3. Course  week duration." +
                            "\n4. Back to main menu." +
                            "\n: INPUT: ");
                    input = commandLineFunctions.getNumber();
                    switch(input) {
                        case 1:
                            course = commandLineFunctions.editCourseName(course);
                            System.out.println(course.getCourseName());
                            break;
                        case 2:
                            course = commandLineFunctions.editCourseStartDate(course);
                            System.out.println(course.getStartDate());
                            break;

                        case 3:
                            course = commandLineFunctions.editCourseDuration(course);
                            System.out.println(course.getWeekDuration());
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Not a valid input!");
                            break;
                    }
                    break;
                case 6:
                    courseList();
                    System.out.println("What course you wanna remove type in the course Id:");
                    courseId = commandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    removed = courseDaoList.removeCourse(course);
                    System.out.println("Course removed: " + removed);
                    break;
                case 7:
                    courseList();
                    System.out.println("What course you wanna register a student to type in the course Id:");
                    courseId = commandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    if(course == null){
                        break;
                    }
                    studentList();
                    System.out.println("What student you wanna register to this course type in the student Id:");
                    studentId = commandLineFunctions.getNumber();
                    student = studentDaoList.findById(studentId);
                    if(student == null){
                        break;
                    }
                    boolean added = commandLineFunctions.registerStudent(course,student);
                    System.out.println("Student added: " + added);
                    break;
                case 8:
                    courseList();
                    System.out.println("What course you wanna unregister a student from type in the course Id:");
                    courseId = commandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    if(course == null){
                        break;
                    }
                    for(int i = 0; i < course.getStudents().size(); i++){
                        System.out.println(course.getStudents().get(i).toString() + "\n");
                    }
                    System.out.println("What student you wanna unregister from this course type in the student Id:");
                    studentId = commandLineFunctions.getNumber();
                    student = studentDaoList.findById(studentId);
                    if(student == null){
                        break;
                    }
                    removed = commandLineFunctions.unregisterStudent(course,student);
                    System.out.println("Student removed: " + removed);
                    break;
                case 9:
                    System.out.print("\nPick a option to find a student with." +
                            "\n1. Find by id." +
                            "\n2. Find by name." +
                            "\n3. Find by email." +
                            "\n4. Back to main menu.\n");

                    input = commandLineFunctions.getNumber();
                    switch(input){
                        case 1:
                            System.out.println("What student id you want to search.");
                            studentId = commandLineFunctions.getNumber();
                            student = studentDaoList.findById(studentId);
                            if(student == null){
                                break;
                            }
                            System.out.println(student.toString());
                            break;
                        case 2:
                            System.out.println("What student name you want to search.");
                            String studentName = commandLineFunctions.getStringFromUser();
                            List<Student> foundStudents = studentDaoList.findByName(studentName);
                            if (foundStudents == null){
                                break;
                            }
                            for(int i = 0; i < foundStudents.size(); i++){
                                System.out.println(foundStudents.get(i).toString() + "\n");
                            }
                            break;
                        case 3:
                            System.out.println("What student email you want to search.");
                            String studentEmail = commandLineFunctions.getStringFromUser();
                            student = studentDaoList.findByEmail(studentEmail);
                            if(student == null){
                                break;
                            }
                            System.out.println(student.toString());
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Not a valid input!");
                            break;
                    }

                    break;
                case 10:
                    System.out.print("\nPick a option to find course with." +
                            "\n1. Find by id." +
                            "\n2. Find by name." +
                            "\n3. Find by start date." +
                            "\n3. Back to main menu.\n");
                    input = commandLineFunctions.getNumber();
                    switch(input){
                        case 1:
                            System.out.println("What course id you want to search.");
                            courseId = commandLineFunctions.getNumber();
                            course = courseDaoList.findById(courseId);
                            if(course == null){
                                break;
                            }
                            System.out.println(course.toString());
                            break;
                        case 2:
                            System.out.println("What course name you want to search.");
                            String courseName = commandLineFunctions.getStringFromUser();
                            List<Course> foundCourses = courseDaoList.findByName(courseName);
                            if(foundCourses == null){
                                break;
                            }
                            for(int i = 0; i < foundCourses.size(); i++){
                                System.out.println(foundCourses.get(i).toString() + "\n");
                            }
                            break;
                        case 3:
                            System.out.println("What course start date you want to search. Year-month-day.");
                            foundCourses = null;
                            boolean wrongFormatCheck = true;
                            while (wrongFormatCheck) {
                                try {
                                    LocalDate date = LocalDate.parse(commandLineFunctions.getStringFromUser(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                    foundCourses = courseDaoList.findByDate(date);
                                    wrongFormatCheck = false;
                                }catch (DateTimeParseException e){
                                    System.out.println("Invalid date format!");
                                }

                            }
                            if(foundCourses == null){
                                break;
                            }
                            for(int i = 0; i < foundCourses.size(); i++){
                                System.out.println(foundCourses.get(i).toString() + "\n");
                            }
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Not a valid input!");
                            break;
                    }
                    break;
                case 11:
                    courseList();
                    System.out.println("What course you wanna check students from type in the course Id:");
                    courseId = commandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    if(course == null){
                        break;
                    }
                    for(int i = 0; i < course.getStudents().size(); i++){
                        System.out.println(course.getStudents().get(i).toString() + "\n");
                    }
                    break;
                case 12:
                   studentList();
                    System.out.println("What student you wanna check courses from type in the student Id:");
                    studentId = commandLineFunctions.getNumber();
                    student = studentDaoList.findById(studentId);
                    if(student == null){
                        break;
                    }
                    for(int i = 0; i < student.getCourseList().size(); i++){
                        System.out.println(student.getCourseList().get(i).toString() + "\n");
                    }
                    break;
                case 13:
                    studentList();
                    break;
                case 14:
                    courseList();
                    break;
                case 15:
                    System.out.println("Bye see you next time.");
                    running = false;
                    break;
                default:
                    System.out.println("Not a valid choose!");
                    break;
            }
        }
    }

    public void courseList(){
        for(int i = 0; i < courseDaoList.findAll().size(); i++){
            System.out.println(courseDaoList.findAll().get(i).toString() + "\n");
        }
    }

    public void studentList(){
        for(int i = 0; i < studentDaoList.findAll().size(); i++){
            System.out.println(studentDaoList.findAll().get(i).toString() + "\n");

        }
    }
}