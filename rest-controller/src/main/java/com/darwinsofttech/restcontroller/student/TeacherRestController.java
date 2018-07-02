package com.darwinsofttech.restcontroller.student;

import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.service.teacher.TeacherRequest;
import com.darwinsofttech.school.service.teacher.TeacherResponse;
import com.darwinsofttech.school.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findOne")
    public TeacherResponse findById(@RequestParam(name = "id") int id) {
        return teacherService.findById(id);
    }

    @PostMapping("/create")
    public TeacherResponse create(@RequestBody TeacherRequest teacherRequest) {
        teacherService.save(teacherRequest.getLastName(), teacherRequest.getFirstName(), teacherRequest.getMiddleName());
        return new TeacherResponse();
    }

    @PostMapping("/update")
    public TeacherResponse update(@RequestBody TeacherRequest teacherRequest) {
        teacherService.update(teacherRequest);
        return new TeacherResponse(
                teacherRequest.getId(),
                teacherRequest.getLastName(),
                teacherRequest.getFirstName(),
                teacherRequest.getMiddleName()
        );
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id") int id) {
        teacherService.remove(id);
    }
}
