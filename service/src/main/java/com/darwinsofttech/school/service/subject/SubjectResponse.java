package com.darwinsofttech.school.service.subject;

import com.darwinsofttech.school.service.schedule.SubjectScheduleResponse;

import java.util.List;

public class SubjectResponse {
    private int id;
    private String subjectCode;
    private String subjectDescription;
    private List<SubjectScheduleResponse> schedules;

    public SubjectResponse() {
    }

    public SubjectResponse(int id, String subjectCode, String subjectDescription) {
        this.id = id;
        this.subjectCode = subjectCode;
        this.subjectDescription = subjectDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public List<SubjectScheduleResponse> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<SubjectScheduleResponse> schedules) {
        this.schedules = schedules;
    }
}
