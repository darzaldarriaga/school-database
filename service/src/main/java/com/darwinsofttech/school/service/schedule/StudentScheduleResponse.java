package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;

public class StudentScheduleResponse {
    private int id;
    private TeacherResponseWithoutScheds teacher;
    private SubjectResponseWithoutScheds subject;

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

    public SubjectResponseWithoutScheds getSubject() {
        return subject;
    }

    public void setSubject(SubjectResponseWithoutScheds subject) {
        this.subject = subject;
    }
}
