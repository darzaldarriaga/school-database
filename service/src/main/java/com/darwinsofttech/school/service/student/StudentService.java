package com.darwinsofttech.school.service.student;

import com.darwinsofttech.school.repository.student.Student;

import java.util.List;

public interface StudentService {
    void save(String lastName, String firstName, String middleName);
    void update(Student student);
    void remove(int studentId);
    List<StudentResponse> findAll();
    Student findById(int studentId);
}
