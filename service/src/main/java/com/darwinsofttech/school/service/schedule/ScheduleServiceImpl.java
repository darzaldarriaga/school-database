package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void assignSchedule(int subjectId, int teacherId) {
        Schedule schedule = new Schedule();
        scheduleRepository.save(schedule);
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void removeSchedule(int scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    @Override
    public List<ScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(schedule -> {
            ScheduleResponse scheduleResponse = mapToScheduleResponse();
            return scheduleResponse;
        }).collect(Collectors.toList());
    }

    private ScheduleResponse mapToScheduleResponse() {
        return null;
    }

    @Override
    public Schedule findById(int scheduleId) {
        return scheduleRepository.findById(scheduleId).get();
    }
}
