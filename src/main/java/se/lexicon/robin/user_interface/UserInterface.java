package se.lexicon.robin.user_interface;

import se.lexicon.robin.Course;
import se.lexicon.robin.Student;
import se.lexicon.robin.data_access.CourseDaoList;
import se.lexicon.robin.data_access.StudentDaoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.zip.DataFormatException;

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
                    "\n11. Get students in course." +
                    "\n12. Show all students." +
                    "\n13. Show all course." +
                    "\n: INPUT: ");

            int input = CommandLineFunctions.getNumber();

            switch (input) {
                case 1:
                    Student student = commandLineFunctions.createNewStudent();
                    student = studentDaoList.saveStudent(student);
                    System.out.println("Student added." +
                            "Student id: " + student.getId() +
                            "\nStudent name: " + student.getName() +
                            "\nStudent address: " + student.getAddress() +
                            "\nStudent email: " + student.getEmail());
                    break;
                case 2:
                    studentList();
                    System.out.println("What student you wanna edit type in the student Id:");
                    int studentId = CommandLineFunctions.getNumber();
                    student = studentDaoList.findById(studentId);
                    if(student == null){
                        break;
                    }
                    System.out.print("\nWhat do you wanna edit?." +
                        "\n1. Student name." +
                        "\n2. Student address." +
                        "\n3. Student email." +
                        "\n: INPUT: ");
                    input = CommandLineFunctions.getNumber();
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
                        default:
                            System.out.println("Not a valid input!");
                            break;
                    }

                    break;
                case 3:
                    studentList();
                    System.out.println("What student you wanna remove type in the student Id:");
                    studentId = CommandLineFunctions.getNumber();
                    student = studentDaoList.findById(studentId);
                    boolean removed = studentDaoList.deleteStudent(student);
                    System.out.println("Student removed: " + removed);
                    break;
                case 4:
                    Course course = commandLineFunctions.createNewCourse();
                    course = courseDaoList.saveCourse(course);
                    System.out.println("course added" +
                            "Course id: " + course.getId() +
                            "\nCourse name: " + course.getCourseName() +
                            "\nCourse start date: " + course.getStartDate() +
                            "\nCourse weeks duration: " + course.getWeekDuration());
                    break;
                case 5:
                    courseList();
                    System.out.println("What course you wanna edit type in the course Id:");
                    int courseId = CommandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    if(course == null){
                        break;
                    }
                    System.out.print("\nWhat do you wanna edit?." +
                            "\n1. Course name." +
                            "\n2. Course start date." +
                            "\n3. Course  week duration." +
                            "\n: INPUT: ");
                    input = CommandLineFunctions.getNumber();
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
                        default:
                            System.out.println("Not a valid input!");
                            break;
                    }
                    break;
                case 6:
                    courseList();
                    System.out.println("What course you wanna remove type in the course Id:");
                    courseId = CommandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    removed = courseDaoList.removeCourse(course);
                    System.out.println("Course removed: " + removed);
                    break;
                case 7:
                    courseList();
                    System.out.println("What course you wanna register a student to type in the course Id:");
                    courseId = CommandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    if(course == null){
                        break;
                    }
                    studentList();
                    System.out.println("What student you wanna register to this course type in the student Id:");
                    studentId = CommandLineFunctions.getNumber();
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
                    courseId = CommandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    if(course == null){
                        break;
                    }
                    for(int i = 0; i < course.getStudents().size(); i++){
                        System.out.println("Student Id: " +  course.getStudents().get(i).getId() +
                        "\nStudent name: " +  course.getStudents().get(i).getName() +
                        "\nStudent Address: " +  course.getStudents().get(i).getAddress() +
                        "\nStudent email: " +  course.getStudents().get(i).getEmail() + "\n");
                    }
                    System.out.println("What student you wanna unregister from this course type in the student Id:");
                    studentId = CommandLineFunctions.getNumber();
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
                            "\n3. Find by email.\n");
                    input = CommandLineFunctions.getNumber();
                    switch(input){
                        case 1:
                            System.out.println("What student id you want to search.");
                            studentId = CommandLineFunctions.getNumber();
                            student = studentDaoList.findById(studentId);
                            if(student == null){
                                break;
                            }
                            System.out.println("Student id: " + student.getId() +
                                    "\nStudent name: " + student.getName() +
                                    "\nStudent address: " + student.getAddress() +
                                    "\nStudent email: " + student.getEmail());
                            break;
                        case 2:
                            System.out.println("What student name you want to search.");
                            String studentName = CommandLineFunctions.getStringFromUser();
                            List<Student> foundStudents = studentDaoList.findByName(studentName);
                            if (foundStudents == null){
                                break;
                            }
                            for(int i = 0; i < foundStudents.size(); i++){
                                System.out.println("Student id: " + foundStudents.get(i).getId() +
                                        "\nStudent name: " + foundStudents.get(i).getName() +
                                        "\nStudent address: " + foundStudents.get(i).getAddress() +
                                        "\nStudent email: " + foundStudents.get(i).getEmail() + "\n");
                            }
                            break;
                        case 3:
                            System.out.println("What student email you want to search.");
                            String studentEmail = CommandLineFunctions.getStringFromUser();
                            student = studentDaoList.findByEmail(studentEmail);
                            if(student == null){
                                break;
                            }
                            System.out.println("Student id: " + student.getId() +
                                    "\nStudent name: " + student.getName() +
                                    "\nStudent address: " + student.getAddress() +
                                    "\nStudent email: " + student.getEmail());
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
                            "\n3. Find by start date.\n");
                    input = CommandLineFunctions.getNumber();
                    switch(input){
                        case 1:
                            System.out.println("What course id you want to search.");
                            courseId = CommandLineFunctions.getNumber();
                            course = courseDaoList.findById(courseId);
                            if(course == null){
                                break;
                            }
                            System.out.println("Course id: " + course.getId() +
                                    "\nCourse name: " + course.getCourseName() +
                                    "\nCourse start date: " + course.getStartDate() +
                                    "\nCourse weeks duration: " + course.getWeekDuration());
                            break;
                        case 2:
                            System.out.println("What course name you want to search.");
                            String courseName = CommandLineFunctions.getStringFromUser();
                            List<Course> foundCourses = courseDaoList.findByName(courseName);
                            if(foundCourses == null){
                                break;
                            }
                            for(int i = 0; i < foundCourses.size(); i++){
                                System.out.println("Course id: " + foundCourses.get(i).getId() +
                                        "\nCourse name: " + foundCourses.get(i).getCourseName() +
                                        "\nCourse start date: " + foundCourses.get(i).getStartDate() +
                                        "\nCourse weeks duration: " + foundCourses.get(i).getWeekDuration() + "\n");
                            }
                            break;
                        case 3:
                            System.out.println("What course start date you want to search. Year-month-day.");
                            foundCourses = null;
                            boolean wrongFormatCheck = true;
                            while (wrongFormatCheck) {
                                try {
                                    LocalDate date = LocalDate.parse(CommandLineFunctions.getStringFromUser(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
                                System.out.println("Course id: " + foundCourses.get(i).getId() +
                                        "\nCourse name: " + foundCourses.get(i).getCourseName() +
                                        "\nCourse start date: " + foundCourses.get(i).getStartDate() +
                                        "\nCourse weeks duration: " + foundCourses.get(i).getWeekDuration() + "\n");
                            }
                            break;
                        default:
                            System.out.println("Not a valid input!");
                            break;
                    }
                    break;
                case 11:
                    courseList();
                    System.out.println("What course you wanna check students from type in the course Id:");
                    courseId = CommandLineFunctions.getNumber();
                    course = courseDaoList.findById(courseId);
                    if(course == null){
                        break;
                    }
                    for(int i = 0; i < course.getStudents().size(); i++){
                        System.out.println("Student Id: " +  course.getStudents().get(i).getId() +
                                "\nStudent name: " +  course.getStudents().get(i).getName() +
                                "\nStudent Address: " +  course.getStudents().get(i).getAddress() +
                                "\nStudent email: " +  course.getStudents().get(i).getEmail() + "\n");
                    }
                    break;
                case 12:
                    studentList();
                    break;
                case 13:
                    courseList();
                    break;
                default:
                    System.out.println("Not a valid input!");
                    break;
            }
        }
    }

    public void courseList(){
        for(int i = 0; i < courseDaoList.findAll().size(); i++){
            System.out.println("Course Id: " + courseDaoList.findAll().get(i).getId() +
            "\nCourse name: " + courseDaoList.findAll().get(i).getCourseName() +
            "\nCourse start date: " + courseDaoList.findAll().get(i).getStartDate() +
            "\nCourse week duration: " + courseDaoList.findAll().get(i).getWeekDuration() + "\n");
        }
    }

    public void studentList(){
        for(int i = 0; i < studentDaoList.findAll().size(); i++){
            System.out.println("Student Id: " + studentDaoList.findAll().get(i).getId() +
            "\nStudent name: " + studentDaoList.findAll().get(i).getName() +
            "\nStudent Address: " + studentDaoList.findAll().get(i).getAddress() +
            "\nStudent email: " + studentDaoList.findAll().get(i).getEmail() + "\n");

        }
    }
}