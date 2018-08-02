package com.darwinsofttech.school.service.student;

import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.student.StudentRepository;
import com.darwinsofttech.school.service.exceptions.CustomException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    private StudentRepository repository;
    private StudentService service;

    @Before
    public void setUp() {
        repository = mock(StudentRepository.class);
        service = new StudentServiceImpl(repository);
    }

    @Test
    public void getReport() throws CustomException {
        when(repository.findAll()).thenReturn(getStudents());
        Assert.assertNotNull(service.getReport());

        try {
            OutputStream out = new FileOutputStream("Student_Reports.pdf");
            out.write(service.getReport());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        Student student = new Student("Lastname", "Firstname", "Middlename");
        List<Student> students = new ArrayList<>();
        students.add(student);
        return students;
    }
}