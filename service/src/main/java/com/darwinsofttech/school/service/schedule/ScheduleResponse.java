package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.service.student.StudentResponseWithoutScheds;
import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;

import java.util.List;

public class ScheduleResponse {
    private int id;
    private SubjectResponseWithoutScheds subjectResponse;
    private TeacherResponseWithoutScheds teacherResponse;
    private List<StudentResponseWithoutScheds> studentResponses;

    public ScheduleResponse(int id,
                            SubjectResponseWithoutScheds subjectResponse,
                            TeacherResponseWithoutScheds teacherResponse,
                            List<StudentResponseWithoutScheds> studentResponses) {

        this.id = id;
        this.subjectResponse = subjectResponse;
        this.teacherResponse = teacherResponse;
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
}
