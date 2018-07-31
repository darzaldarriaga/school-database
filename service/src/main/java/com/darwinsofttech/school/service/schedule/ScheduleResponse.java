package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.service.student.StudentResponseWithoutScheds;
import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;

import java.util.List;

public class ScheduleResponse {
    private int id;
    private SubjectResponseWithoutScheds subject;
    private TeacherResponseWithoutScheds teacher;
    private String days;
    private String time;
    private List<StudentResponseWithoutScheds> students;

    public ScheduleResponse() {
    }

    public ScheduleResponse(int id,
                            SubjectResponseWithoutScheds subject,
                            TeacherResponseWithoutScheds teacher,
                            String days,
                            String time,
                            List<StudentResponseWithoutScheds> students) {

        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.days = days;
        this.time = time;
        this.students = students;
    }

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

    public TeacherResponseWithoutScheds getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherResponseWithoutScheds teacher) {
        this.teacher = teacher;
    }

    public List<StudentResponseWithoutScheds> getStudents() {
        return students;
    }

    public void setStudentRs(List<StudentResponseWithoutScheds> students) {
        this.students = students;
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
