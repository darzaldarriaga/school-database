package com.darwinsofttech.restcontroller.student;

import com.darwinsofttech.school.service.teacher.TeacherResponse;
import com.darwinsofttech.school.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherRestController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public List<TeacherResponse> findAll() {
        return teacherService.findAll();
    }
}
