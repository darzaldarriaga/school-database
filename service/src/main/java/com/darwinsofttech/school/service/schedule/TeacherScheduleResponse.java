package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.service.student.StudentResponse;
import com.darwinsofttech.school.service.student.StudentResponseWithoutScheds;
import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;

import java.util.List;

public class TeacherScheduleResponse {
    private int id;
    private SubjectResponseWithoutScheds subject;
    private List<StudentResponseWithoutScheds> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubjectResponseWithoutScheds getSubject() {
        return subject;
    }

    public void setSubject(SubjectResponseWithoutScheds subject) {
        this.subject = subject;
    }

    public List<StudentResponseWithoutScheds> getStudents() {
        return students;
    }

    public void setStudents(List<StudentResponseWithoutScheds> students) {
        this.students = students;
    }
}
