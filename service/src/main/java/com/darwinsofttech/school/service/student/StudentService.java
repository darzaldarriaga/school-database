package com.darwinsofttech.school.service.student;

import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.service.exceptions.CustomException;

import java.util.List;

public interface StudentService {
    Student save(String lastName, String firstName, String middleName);
    void update(StudentRequest studentRequest);
    void remove(int studentId);
    List<StudentResponse> findAll();
    StudentResponse findById(int studentId);
    byte[] getReport() throws CustomException;
}
