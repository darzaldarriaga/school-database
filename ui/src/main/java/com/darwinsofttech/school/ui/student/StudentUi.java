package com.darwinsofttech.school.ui.student;

import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.service.student.StudentService;
import com.darwinsofttech.school.ui.Reader;

import java.io.IOException;
import java.util.List;

public class StudentUi {
    private StudentService studentService;

    public StudentUi() {
    }

    public StudentUi(StudentService studentService) {
        this.studentService = studentService;
    }

    public void registerStudent() throws IOException {
        String lastName = askForLastName();
        String firstName = askForFirstName();
        String middleName = askForMiddleName();

        studentService.save(lastName, firstName, middleName);
    }

    public void updateStudent() throws IOException {
        Student student = new Student();
        student.setStudentId(askForStudentId());
        student.setLastName(askForLastName());
        student.setFirstName(askForFirstName());
        student.setMiddleName(askForMiddleName());
        studentService.update(student);
    }

    public void removeStudent() throws IOException {
        int studentId = askForStudentId();
        studentService.remove(askForStudentId());
    }

    public void displayAll() {
        List<Student> students = studentService.searchAll();
        students.forEach(System.out::println);
    }

    public void displaySearched() throws IOException {
        int studentId = askForStudentId();
        System.out.println(studentService.searchById(studentId));
    }

    public String askForLastName() throws IOException {
        System.out.print("Enter last name: ");
        return Reader.INSTANCE.getReader().readLine();
    }

    public String askForFirstName() throws IOException {
        System.out.print("Enter first name: ");
        return Reader.INSTANCE.getReader().readLine();
    }

    public String askForMiddleName() throws IOException {
        System.out.print("Enter middle name (Enter 0 if no applicable): ");
        return Reader.INSTANCE.getReader().readLine();
    }

    public int askForStudentId() throws IOException {
        System.out.print("Enter id number: ");
        try {
            return Integer.parseInt(Reader.INSTANCE.getReader().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct id");
            return askForStudentId();
        }
    }
}
