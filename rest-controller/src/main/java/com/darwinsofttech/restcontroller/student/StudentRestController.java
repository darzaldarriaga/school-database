package com.darwinsofttech.restcontroller.student;

import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.service.exceptions.CustomException;
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
        Student newStudent = studentService.save(studentRequest.getLastName(), studentRequest.getFirstName(), studentRequest.getMiddleName());
        return new StudentResponse(newStudent.getId(), newStudent.getLastName(), newStudent.getFirstName(), newStudent.getMiddleName());
    }

    @PostMapping("/update")
    public StudentResponse update(@RequestBody StudentRequest studentRequest) {
        studentService.update(studentRequest);
        return new StudentResponse(studentRequest.getId(), studentRequest.getLastName(),
                studentRequest.getFirstName(), studentRequest.getMiddleName());
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id") int id) {
        studentService.remove(id);
    }

    @GetMapping("/getReport")
    public byte[] getReport() {
        try {
            return studentService.getReport();
        } catch (CustomException e) {
            e.printStackTrace();
        }
        return null;
    }
}
