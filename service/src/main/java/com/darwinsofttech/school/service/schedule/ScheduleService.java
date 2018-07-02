package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.repository.schedule.Schedule;

import java.util.List;

public interface ScheduleService {
    void create(ScheduleRequest scheduleRequest);
    void update(ScheduleRequest scheduleRequest);
    void addStudent(ScheduleRequest scheduleRequest);
    void delete(int scheduleId);
    List<ScheduleResponse> findAll();
    ScheduleResponse findById(int scheduleId);
}
