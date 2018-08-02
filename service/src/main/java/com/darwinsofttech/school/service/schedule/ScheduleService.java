package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.service.exceptions.CustomException;

import java.util.List;

public interface ScheduleService {
    void create(ScheduleRequest scheduleRequest);
    void update(ScheduleRequest scheduleRequest);
    void addStudent(ScheduleRequest scheduleRequest);
    void removeStudent(ScheduleRequest scheduleRequest);
    void delete(int scheduleId);
    List<ScheduleResponse> findAll();
    ScheduleResponse findById(int scheduleId);
    byte[] getReport() throws CustomException;
}
