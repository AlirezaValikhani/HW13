import exceptions.UserNameNotFoundException;
import models.*;
import org.hibernate.SessionFactory;
import repositories.*;
import services.*;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Menu {
    SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    Scanner scanner = new Scanner(System.in);
    StudentService studentService = new StudentService();
    ProfessorService professorService = new ProfessorService();
    EmployeeService employeeService = new EmployeeService();
    CourseService courseService = new CourseService();
    StudentCourseService studentCourseService = new StudentCourseService();
    TermService termService = new TermService();


    public void firstMenu() {
        while (true) {
            System.out.println("Welcome to university system\n" +
                    "1. Student logIn \n" +
                    "2. Professor logIn\n" +
                    "3. Employee logIn\n" +
                    "4. Search by student userName \n" +
                    "5. Exit");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    studentLogIn();
                    break;
                case 2:
                    professorLogIn();
                    break;
                case 3:
                    employeeLogIn();
                    break;
                case 4:
                    searchByStudentUserName();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter number between 1 upto 3");
            }
        }
    }


    //////////////////////   Employee part   ////////////////////

    public void searchByEmployeeUserName() {
        System.out.println("Enter your user name : ");
        String userName = scanner.next();
        if (employeeService.findByUserName(userName) != null) {
            System.out.println(employeeService.findByUserName(userName).toString() + "\n");
            firstMenu();
        }else {
            System.out.println("-------------------------\n Invalid user name!!! \n-------------------------");
            searchByStudentUserName();
        }
    }

    public void employeeLogIn() {
        String username, password;
        do {
            System.out.println("Username: ");
            username = scanner.next();
            System.out.println("Password: ");
            password = scanner.next();
            if ((employeeService.findByUserName(username) != null) &&
                    (employeeService.findByUserName(username).getPassword().equals(password))) {
                employeeService.findByUserName(username);
                employeeMenu();
                break;
            } else
                System.out.println("Wrong username or password!");
        } while (true);
    }

    public void employeeMenu() {
        while(true) {
            System.out.println("1. Student section \n" +
                    "2. Professor section \n" +
                    "3. Employee section\n" +
                    "4. Course section\n" +
                    "5. Salary section\n" +
                    "6. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    studentSection();
                    break;
                case 2:
                    professorSection();
                    break;
                case 3:
                    employeeSection();
                    break;
                case 4:
                    courseSection();
                    break;
                case 5:
                    /*twitPart(user);*/
                    break;
                case 6:
                    firstMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }


    public void studentSection() {
        while(true) {
            System.out.println("1. Add student \n" +
                    "2. Delete student \n" +
                    "3. Edit student\n" +
                    "4. See all students\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    editStudent();
                    break;
                case 4:
                    seeAllStudents();
                    break;
                case 5:
                    employeeMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void addStudent() {
        while (true) {
            String userName = "";
            try {
                System.out.println("User name: ");
                userName = scanner.next();
            }
            catch (UserNameNotFoundException u){
                u.getMessage();
            }
            System.out.println("Password: ");
            String password = scanner.next();
            System.out.println("First name: ");
            String firstName = scanner.next();
            System.out.println("Last name: ");
            String lastName = scanner.next();
            System.out.println("National code: ");
            String nationalCode = scanner.next();
            System.out.println("Phone number: ");
            String phoneNumber = scanner.next();
            System.out.println("Term number: ");
            String termNumber = scanner.next();
            Term term = new Term(termNumber);
            termService.save(term);
            Student student = new Student(userName,password,firstName,lastName,nationalCode,phoneNumber,term,new HashSet<>());
            Student student1 = studentService.save(student);
            System.out.println("---------------\nStudent id : " + student1.getId() + "\n---------------" );
            studentSection();
        }
    }

    public void deleteStudent(){
        System.out.println("Enter student id : ");
        Long studentId = scanner.nextLong();
        if(studentService.findById(studentId) != null){
            Student deleteStudent =studentService.findById(studentId);
            studentService.delete(deleteStudent);
            System.out.println("---------------------------------------------\nStudent number " + studentId + " deleted successfully\n---------------------------------------------");
            studentSection();
        }else {
            System.out.println("------------------------\nThis ID does not exist\n------------------------");
            deleteStudent();
        }
    }

    public void editStudent() {
        while (true) {
            System.out.println("Enter student id : ");
            Long studentId = scanner.nextLong();
            Student student = new Student(studentId);
            if (studentService.findById(student.getId()) != null) {
                System.out.println("Edit user name : ");
                String userName = scanner.next();
                System.out.println("Edit password : ");
                String password = scanner.next();
                System.out.println("Edit first name : ");
                String firstName = scanner.next();
                System.out.println("Edit last name : ");
                String lastName = scanner.next();
                System.out.println("Edit national code : ");
                String nationalCode = scanner.next();
                System.out.println("Edit phone number : ");
                String phoneNumber = scanner.next();
                Student student1 = new Student(studentId,userName, password, firstName, lastName, nationalCode, phoneNumber);
                studentService.update(student1);
                System.out.println("------------------------\nUpdate was successful\n------------------------");
                studentSection();
            }else {
                System.out.println("------------------------------\nID not found.Try again\n------------------------------");
                editStudent();
            }
        }
    }

    public void seeAllStudents(){
        List<Student> students = studentService.findAll();
        for (Student s: students) {
            System.out.println(s.toString());
        }
        System.out.println();
        studentSection();
    }

    public void professorSection() {
        while(true) {
            System.out.println("1. Add professor \n" +
                    "2. Delete professor \n" +
                    "3. Edit professor\n" +
                    "4. See all professors\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    addProfessor();
                    break;
                case 2:
                    deleteProfessor();
                    break;
                case 3:
                    editProfessor();
                    break;
                case 4:
                    seeAllProfessor();
                    break;
                case 5:
                    employeeMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void addProfessor() {
        while (true) {
            String userName = "";
            try {
                System.out.println("User name: ");
                userName = scanner.next();
            }
            catch (UserNameNotFoundException u){
                u.getMessage();
            }
            System.out.println("Password: ");
            String password = scanner.next();
            System.out.println("First name: ");
            String firstName = scanner.next();
            System.out.println("Last name: ");
            String lastName = scanner.next();
            System.out.println("National code: ");
            String nationalCode = scanner.next();
            System.out.println("Phone number: ");
            String phoneNumber = scanner.next();
            System.out.println("Salary: ");
            Double salary = scanner.nextDouble();
            System.out.println("Course name: ");
            String courseName = scanner.next();
            System.out.println("Course unit: ");
            Integer courseUnit = scanner.nextInt();
            Course course = new Course(courseName,courseUnit);
            Course returnedCourse = courseService.save(course);
            Course course1 = new Course(returnedCourse.getId(),courseName,courseUnit);
            Professor professor = new Professor(userName, password, firstName, lastName,
                    nationalCode,phoneNumber,salary,course1);
            Professor returnedProfessor = professorService.save(professor);
            System.out.println("-------------------\nProfessor id : " + returnedProfessor.getId() + "\nCourse id : " + returnedCourse.getId() + " \n-------------------" );
            professorSection();
        }
    }

    public void deleteProfessor(){
        System.out.println("Enter professor id : ");
        Long professorId = scanner.nextLong();
        if(professorService.findById(professorId) != null){
            Professor deleteProfessor = professorService.findById(professorId);
            professorService.delete(deleteProfessor);
            System.out.println("--------------------------------------------------------------\nProfessor number " + professorId + " deleted successfully\n--------------------------------------------------------------");
            professorSection();
        }else {
            System.out.println("--------------------------\nThis ID does not exist\n--------------------------");
            deleteProfessor();
        }
    }

    public void editProfessor() {
        while (true) {
            System.out.println("Enter professor id : ");
            Long professorId = scanner.nextLong();
            Professor professor = new Professor(professorId);
            if (professorService.findById(professorId) != null) {
                System.out.println("Edit user name : ");
                String userName = scanner.next();
                System.out.println("Edit password : ");
                String password = scanner.next();
                System.out.println("Edit first name : ");
                String firstName = scanner.next();
                System.out.println("Edit last name : ");
                String lastName = scanner.next();
                System.out.println("Edit national code : ");
                String nationalCode = scanner.next();
                System.out.println("Edit phone number : ");
                String phoneNumber = scanner.next();
                System.out.println("Edit salary : ");
                Double salary = scanner.nextDouble();
                System.out.println("Edit course name : ");
                String courseName = scanner.next();
                System.out.println("Edit unit : ");
                Integer unit = scanner.nextInt();
                Course course = new Course(courseName, unit);
                Course returnedCourse = courseService.save(course);
                Course course1 = new Course(returnedCourse.getId(),courseName,unit);
                Professor professor1 = new Professor(professorId,userName, password, firstName, lastName, nationalCode, phoneNumber,salary,course1);
                professorService.update(professor1);
                System.out.println("----------------------------\nUpdate was successful\n----------------------------");
                professorSection();
            }else System.out.println("------------------------------\nID not found.Try again\n------------------------------");
        }
    }

    public void seeAllProfessor(){
        List<Professor> professors = professorService.findAll();
        for (Professor p: professors) {
            System.out.println(p.toString());
        }
        System.out.println();
        professorSection();
    }

    public void employeeSection() {
        while(true) {
            System.out.println("1. Add employee \n" +
                    "2. Delete employee \n" +
                    "3. Edit employee\n" +
                    "4. See all employees\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    deleteEmployee();
                    break;
                case 3:
                    editEmployee();
                    break;
                case 4:
                    seeAllEmployees();
                    break;
                case 5:
                    employeeMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void addEmployee() {
        while (true) {
            String userName = "";
            try {
                System.out.println("User name: ");
                userName = scanner.next();
            }
            catch (UserNameNotFoundException u){
                u.getMessage();
            }
            System.out.println("Password: ");
            String password = scanner.next();
            System.out.println("First name: ");
            String firstName = scanner.next();
            System.out.println("Last name: ");
            String lastName = scanner.next();
            System.out.println("National code: ");
            String nationalCode = scanner.next();
            System.out.println("Phone number: ");
            String phoneNumber = scanner.next();
            System.out.println("Salary: ");
            Double salary = scanner.nextDouble();
            Employee employee = new Employee(userName, password, firstName, lastName,
                    nationalCode,phoneNumber,salary);
            Employee returnedEmployee = employeeService.save(employee);
            System.out.println("---------------\nEmployee id : " + returnedEmployee.getId() + "\n---------------");
            employeeSection();
        }
    }

    public void deleteEmployee(){
        System.out.println("Enter employee id : ");
        Long employeeId = scanner.nextLong();
        if(employeeService.findById(employeeId) != null){
            Employee deleteEmployee = employeeService.findById(employeeId);
            employeeService.delete(deleteEmployee);
            System.out.println("--------------------------------------------------\nEmployee number " + employeeId + " deleted successfully\n--------------------------------------------------");
            employeeSection();
        }else {
            System.out.println("------------------------\nThis ID does not exist\n------------------------");
            deleteEmployee();
        }
    }

    public void editEmployee() {
        while (true) {
            System.out.println("Enter employee id : ");
            Long employeeId = scanner.nextLong();
            Employee employee = new Employee(employeeId);
            if (employeeService.findById(employee.getId()) != null) {
                System.out.println("Edit user name : ");
                String userName = scanner.next();
                System.out.println("Edit password : ");
                String password = scanner.next();
                System.out.println("Edit first name : ");
                String firstName = scanner.next();
                System.out.println("Edit last name : ");
                String lastName = scanner.next();
                System.out.println("Edit national code : ");
                String nationalCode = scanner.next();
                System.out.println("Edit phone number : ");
                String phoneNumber = scanner.next();
                System.out.println("Edit salary : ");
                Double salary = scanner.nextDouble();
                Employee employee1 = new Employee(employeeId,userName, password, firstName, lastName, nationalCode, phoneNumber,salary);
                employeeService.update(employee1);
                System.out.println("----------------------------\nUpdate was successful\n----------------------------");
                employeeSection();
            }else System.out.println("------------------------------\nID not found.Try again\n------------------------------");
            editEmployee();
        }
    }

    public void seeAllEmployees(){
        List<Employee> employees = employeeService.findAll();
        for (Employee e: employees) {
            System.out.println(e.toString());
        }
        System.out.println();
        employeeSection();
    }


    public void courseSection() {
        while(true) {
            System.out.println("1. Add course \n" +
                    "2. Delete course \n" +
                    "3. Edit course\n" +
                    "4. See all courses\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    deleteCourse();
                    break;
                case 3:
                    editCourse();
                    break;
                case 4:
                    seeAllCourses();
                    break;
                case 5:
                    employeeMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void addCourse() {
        while (true) {
            System.out.println("Course name : ");
            String courseName = scanner.next();
            System.out.println("Unit : ");
            Integer unit = scanner.nextInt();
            Course course = new Course(courseName, unit);
            courseService.save(course);
            System.out.println("---------------\nCourse id : " + course.getId() + "\n---------------");
            courseSection();
        }
    }

    public void deleteCourse(){
        System.out.println("Enter course id : ");
        Long courseId = scanner.nextLong();
        if(courseService.findById(courseId) != null){
            Course course = courseService.findById(courseId);
            courseService.delete(course);
            System.out.println("----------------------------------------------------\nCourse number " + courseId + " deleted successfully\n----------------------------------------------------");
            courseSection();
        }else {
            System.out.println("---------------------------\nThis ID does not exist\n---------------------------");
            deleteCourse();
        }
    }

    public void editCourse() {
        while (true) {
            System.out.println("Enter course id : ");
            Long courseId = scanner.nextLong();
            Course course = new Course(courseId);
            if (courseService.findById(course.getId()) != null) {
                System.out.println("Edit course name : ");
                String courseName = scanner.next();
                System.out.println("Edit unit : ");
                Integer unit = scanner.nextInt();
                Course course1 = new Course(courseId,courseName,unit);
                courseService.update(course1);
                System.out.println("-------------------------\nUpdate was successful\n-------------------------");
                courseSection();
            }else {
                System.out.println("----------------------------\nID doesnt exists.Try again\n----------------------------");
                editCourse();
            }
        }
    }

    public void seeAllCourses(){
        List<Course> courses= courseService.findAll();
        for (Course c: courses) {
            System.out.println(c.toString());
        }
        System.out.println();
        courseSection();
    }


    ///////////////////////   Professor part    //////////////////////


    public void searchByProfessorUserName() {
        System.out.println("Enter your user name : ");
        String userName = scanner.next();
        if (professorService.findByUserName(userName) != null) {
            System.out.println(professorService.findByUserName(userName).toString() + "\n");
            firstMenu();
        }else {
            System.out.println("-------------------------\n Invalid user name!!! \n-------------------------");
            searchByProfessorUserName();
        }
    }

    public void professorRegister() {
        while (true) {
            System.out.println("Please Enter your username: ");
            String username = scanner.next();
            if (professorService.findByUserName(username) != null) {
                System.out.println("This username exists please choose another username");
                professorRegister();
            } else {
                System.out.println("Password: ");
                String password = scanner.next();
                System.out.println("First name: ");
                String firstName = scanner.next();
                System.out.println("Last name: ");
                String lastName = scanner.next();
                System.out.println("National code: ");
                String nationalCode = scanner.next();
                System.out.println("Phone number: ");
                String phoneNumber = scanner.next();
                System.out.println("Salary: ");
                Double salary = scanner.nextDouble();
                System.out.println("Course name: ");
                String courseName = scanner.next();
                System.out.println("Course unit: ");
                Integer courseUnit = scanner.nextInt();
                Course course = new Course(courseName,courseUnit);
                Professor professor = new Professor(username, password, firstName, lastName,
                        nationalCode,phoneNumber,salary,course);
                Professor returnedProfessor = professorService.save(professor);
                System.out.println("---------------\nUser id : \n---------------" + returnedProfessor.getId());
                firstMenu();
            }
        }
    }

    public void professorLogIn() {
        String username, password;
        do {
            System.out.println("Username: ");
            username = scanner.next();
            System.out.println("Password: ");
            password = scanner.next();
            if ((professorService.findByUserName(username) != null) &&
                    (professorService.findByUserName(username).getPassword().equals(password))) {
                Professor professorId = professorService.findByUserName(username);
                professorMenu(professorId);
                break;
            } else
                System.out.println("Wrong username or password!");
        } while (true);
    }

    public void professorMenu(Professor professor) {
        while(true) {
            System.out.println("1. Add new student \n" +
                    "2. Delete student \n" +
                    "3. Edit student\n" +
                    "4. Edit your account\n" +
                    "5. Twit part\n" +
                    "6. Comment part\n" +
                    "7. See all twits\n" +
                    "8. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    /*addAccount();*/
                    break;
                case 2:
                    /*seeAccount(user);*/
                    break;
                case 3:
                    /*deleteAccount(user);
                    break*/;
                case 4:
                    /*editAccount(user);*/
                    break;
                case 5:
                    /*twitPart(user);*/
                    break;
                case 6:
                    /*commentPart(user);*/
                    break;
                case 7:
                    /*seeAllTwits(user);*/
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    ///////////////////////   Student part    //////////////////////

    public void searchByStudentUserName() {
        System.out.println("Enter your user name : ");
        String userName = scanner.next();
        if (studentService.findByUserName(userName) != null) {
            System.out.println(studentService.findByUserName(userName).toString() + "\n");
            studentService.findByUserName(userName);
            firstMenu();
        }else {
            System.out.println("-------------------------\n Invalid user name!!! \n-------------------------");
            searchByStudentUserName();
        }
    }

    public void studentLogIn() {
        String username, password;
        do {
            System.out.println("Username: ");
            username = scanner.next();
            System.out.println("Password: ");
            password = scanner.next();
            if ((studentService.findByUserName(username) != null) &&
                    (studentService.findByUserName(username).getPassword().equals(password))) {
                Student student = studentService.findByUserName(username);
                studentMenu(student);
                break;
            } else
                System.out.println("-------------------------\nWrong username or password!\n-------------------------");
        } while (true);
    }

    public void studentMenu(Student student) {
        while(true) {
            System.out.println("1. View your profile \n" +
                    "2. See course list \n" +
                    "3. Select unit\n" +
                    "4. See your courses\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    seeProfile(student);
                    break;
                case 2:
                    seeCourseList(student);
                    break;
                case 3:
                    selectUnit(student);
                    break;
                case 4:
                    seeYourCourses(student);
                    break;
                case 5:
                    /*twitPart(user);*/
                    break;
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void seeProfile(Student student){
        System.out.println(studentService.findById(student.getId()).toString());
        studentMenu(student);
    }

    public void seeCourseList(Student student){
        List<Course> courses = courseService.findAll();
        for (Course course:courses) {
            System.out.println(course.toString());
        }
        studentMenu(student);
    }

    public void selectUnit(Student student){
        List<Course> courses = courseService.findAll();
        for (Course course:courses) {
            System.out.println(course.toString());
        }
        System.out.println();
        while (true) {
            System.out.println("(Enter 0 to exit)\nEnter course ID : ");
            Long courseId = scanner.nextLong();
            Course course1 = new Course(courseId);
            if(courseService.findById(course1.getId()) != null){
                Course course = courseService.findById(course1.getId());
                Student_Course student_course = new Student_Course(student, course, null);
                if (studentCourseService.findByStudentAndCourseId(student_course) == null) {
                    studentCourseService.save(student_course);
                    System.out.println("--------------\nCourse added\n--------------");
                } else System.out.println("------------------\nDuplicate course\n------------------");
            }else if(courseId == 0){
                break;
            }else {
                System.out.println("--------------------------\nInvalid ID.Try again\n--------------------------");
                selectUnit(student);
            }
        }
    }

    public void seeYourCourses(Student student){
        Student_Course stc = new Student_Course(student);
        List<Student_Course> studentToCourses = studentCourseService.findByStudentAndCourseId(stc);
        for (Student_Course stc1:studentToCourses) {
            System.out.println(stc1.toString());
        }
    }
}

