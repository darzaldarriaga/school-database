package com.darwinsofttech.restcontroller.student;

import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.service.subject.SubjectResponse;
import com.darwinsofttech.school.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/subject")
public class SubjectRestController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/list")
    public List<SubjectResponse> findAll() {
        return subjectService.findAll();
    }
}
