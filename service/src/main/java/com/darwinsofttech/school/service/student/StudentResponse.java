package com.darwinsofttech.school.service.student;

import com.darwinsofttech.school.service.schedule.StudentScheduleResponse;

import java.util.List;

public class StudentResponse {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private List<StudentScheduleResponse> schedules;

    public StudentResponse() {
    }

    public StudentResponse(int id, String lastName, String firstName, String middleName) {
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

    public List<StudentScheduleResponse> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<StudentScheduleResponse> schedules) {
        this.schedules = schedules;
    }
}
