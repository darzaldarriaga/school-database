package com.darwinsofttech.restcontroller.student;

import com.darwinsofttech.school.service.student.StudentRequest;
import com.darwinsofttech.school.service.student.StudentResponse;
import com.darwinsofttech.school.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findOne")
    public StudentResponse findById(@RequestParam(name = "id") int id) {
        return studentService.findById(id);
    }

    @GetMapping("/{id}")
    public StudentResponse findById2(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PostMapping("/create")
    public StudentResponse create(@RequestBody StudentRequest studentRequest) {
        System.out.println(studentRequest);
        studentService.save(studentRequest.getLastName(), studentRequest.getFirstName(), studentRequest.getMiddleName());
        return new StudentResponse();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id") int id) {
        studentService.remove(id);
    }

    @PostMapping("/update")
    public StudentResponse update(@RequestBody StudentRequest studentRequest) {
        System.out.println(studentRequest);
        studentService.update(studentRequest);
        return new StudentResponse(studentRequest.getId(), studentRequest.getLastName(),
                studentRequest.getFirstName(), studentRequest.getMiddleName());
    }
}
