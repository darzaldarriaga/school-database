package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.service.student.StudentResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;

import java.util.List;

public class SubjectScheduleResponse {
    private int id;
    private TeacherResponseWithoutScheds teacher;
    private List<StudentResponseWithoutScheds> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TeacherResponseWithoutScheds getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherResponseWithoutScheds teacher) {
        this.teacher = teacher;
    }

    public List<StudentResponseWithoutScheds> getStudents() {
        return students;
    }

    public void setStudents(List<StudentResponseWithoutScheds> students) {
        this.students = students;
    }
}
