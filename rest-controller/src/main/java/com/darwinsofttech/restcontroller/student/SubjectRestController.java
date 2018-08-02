package com.darwinsofttech.restcontroller.student;

import com.darwinsofttech.school.service.exceptions.CustomException;
import com.darwinsofttech.school.service.subject.SubjectRequest;
import com.darwinsofttech.school.service.subject.SubjectResponse;
import com.darwinsofttech.school.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findOne")
    public SubjectResponse findById(@RequestParam(name = "id") int id) {
        return subjectService.findById(id);
    }

    @PostMapping("/create")
    public SubjectResponse create(@RequestBody SubjectRequest subjectRequest) {
        subjectService.save(subjectRequest.getSubjectCode(), subjectRequest.getSubjectDescription());
        return new SubjectResponse();
    }

    @PostMapping("/update")
    public SubjectResponse update(@RequestBody SubjectRequest subjectRequest) {
        subjectService.update(subjectRequest);
        return new SubjectResponse(subjectRequest.getId(), subjectRequest.getSubjectCode(),
                subjectRequest.getSubjectDescription());
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id") int id) {
        subjectService.remove(id);
    }

    @GetMapping("/getReport")
    public byte[] getReport() {
        try {
            return subjectService.getReport();
        } catch (CustomException e) {
            e.printStackTrace();
        }
        return null;
    }
}
