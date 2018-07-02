package com.darwinsofttech.school.service.teacher;

import com.darwinsofttech.school.repository.teacher.Teacher;

import java.util.List;

public interface TeacherService {
    void save(String lastName, String firstName, String middleName);
    void update(TeacherRequest teacherRequest);
    void remove(int teacherId);
    List<TeacherResponse> findAll();
    TeacherResponse findById(int teacherId);
}
