package com.darwinsofttech.restcontroller.student;

import com.darwinsofttech.school.service.student.StudentResponse;
import com.darwinsofttech.school.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<StudentResponse> findAll() {
        return studentService.findAll();
    }
}
