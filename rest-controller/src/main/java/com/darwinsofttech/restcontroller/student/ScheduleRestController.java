package com.darwinsofttech.restcontroller.student;

import com.darwinsofttech.school.service.schedule.ScheduleResponse;
import com.darwinsofttech.school.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
