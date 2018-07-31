package com.darwinsofttech.restcontroller.student;

import com.darwinsofttech.school.service.schedule.ScheduleRequest;
import com.darwinsofttech.school.service.schedule.ScheduleResponse;
import com.darwinsofttech.school.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleRestController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/list")
    public List<ScheduleResponse> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/findOne")
    public ScheduleResponse findById(@RequestParam(name = "id") int id) {
        return scheduleService.findById(id);
    }

    @PostMapping("/create")
    public ScheduleResponse create(@RequestBody ScheduleRequest scheduleRequest) {
        scheduleService.create(scheduleRequest);
        return new ScheduleResponse();
    }

    @PostMapping("/update")
    public ScheduleResponse update(@RequestBody ScheduleRequest scheduleRequest) {
        scheduleService.update(scheduleRequest);
        return scheduleService.findById(scheduleRequest.getId());
    }

    @PostMapping("/addStudent")
    public ScheduleResponse addStudent(@RequestBody ScheduleRequest scheduleRequest) {
        scheduleService.addStudent(scheduleRequest);
        return scheduleService.findById(scheduleRequest.getId());
    }

    @PostMapping("/removeStudent")
    public ScheduleResponse removeStudent(@RequestBody ScheduleRequest scheduleRequest) {
        scheduleService.removeStudent(scheduleRequest);
        return scheduleService.findById(scheduleRequest.getId());
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id") int id) {
        scheduleService.delete(id);
    }
}
