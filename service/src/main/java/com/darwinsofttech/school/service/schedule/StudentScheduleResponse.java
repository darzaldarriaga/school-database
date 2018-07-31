package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;

public class StudentScheduleResponse {
    private int id;
    private TeacherResponseWithoutScheds teacher;
    private SubjectResponseWithoutScheds subject;
    private String days;
    private String time;

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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
