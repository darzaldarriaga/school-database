package com.darwinsofttech.school.service.student;

import com.darwinsofttech.school.repository.student.Student;

import java.util.List;

public interface StudentService {
    void save(String lastName, String firstName, String middleName);
    void update(StudentRequest studentRequest);
    void remove(int studentId);
    List<StudentResponse> findAll();
    StudentResponse findById(int studentId);
}
