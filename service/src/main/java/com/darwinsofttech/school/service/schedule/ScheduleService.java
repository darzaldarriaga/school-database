package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.repository.schedule.Schedule;

import java.util.List;

public interface ScheduleService {
    void assignSchedule(int subjectId, int teacherId);
    void updateSchedule(Schedule schedule);
    void removeSchedule(int scheduleId);
    List<Schedule> searchAll();
    Schedule searchById(int scheduleId);
}
