package com.darwinsofttech.school.service.teacher;

import com.darwinsofttech.school.service.exceptions.CustomException;

import java.util.List;

public interface TeacherService {
    void save(String lastName, String firstName, String middleName);
    void update(TeacherRequest teacherRequest);
    void remove(int teacherId);
    List<TeacherResponse> findAll();
    TeacherResponse findById(int teacherId);
    byte[] getReport() throws CustomException;
}
