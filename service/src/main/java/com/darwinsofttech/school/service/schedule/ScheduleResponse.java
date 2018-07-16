package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.service.student.StudentResponseWithoutScheds;
import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;

import java.util.List;

public class ScheduleResponse {
    private int id;
    private SubjectResponseWithoutScheds subjectResponse;
    private TeacherResponseWithoutScheds teacherResponse;
    private String days;
    private String time;
    private List<StudentResponseWithoutScheds> studentResponses;

    public ScheduleResponse() {
    }

    public ScheduleResponse(int id,
                            SubjectResponseWithoutScheds subjectResponse,
                            TeacherResponseWithoutScheds teacherResponse,
                            String days,
                            String time,
                            List<StudentResponseWithoutScheds> studentResponses) {

        this.id = id;
        this.subjectResponse = subjectResponse;
        this.teacherResponse = teacherResponse;
        this.days = days;
        this.time = time;
        this.studentResponses = studentResponses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubjectResponseWithoutScheds getSubjectResponse() {
        return subjectResponse;
    }

    public void setSubjectResponse(SubjectResponseWithoutScheds subjectResponse) {
        this.subjectResponse = subjectResponse;
    }

    public TeacherResponseWithoutScheds getTeacherResponse() {
        return teacherResponse;
    }

    public void setTeacherResponse(TeacherResponseWithoutScheds teacherResponse) {
        this.teacherResponse = teacherResponse;
    }

    public List<StudentResponseWithoutScheds> getStudentResponses() {
        return studentResponses;
    }

    public void setStudentResponses(List<StudentResponseWithoutScheds> studentResponses) {
        this.studentResponses = studentResponses;
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
