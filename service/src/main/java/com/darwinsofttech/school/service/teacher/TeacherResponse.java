package com.darwinsofttech.school.service.teacher;

import com.darwinsofttech.school.service.schedule.TeacherScheduleResponse;

import java.util.List;

public class TeacherResponse {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private List<TeacherScheduleResponse> schedules;

    public TeacherResponse() {
    }

    public TeacherResponse(int id, String lastName, String firstName, String middleName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public List<TeacherScheduleResponse> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<TeacherScheduleResponse> schedules) {
        this.schedules = schedules;
    }
}
