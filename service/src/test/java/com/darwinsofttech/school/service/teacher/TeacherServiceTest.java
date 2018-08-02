package com.darwinsofttech.school.service.teacher;

import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.repository.teacher.TeacherRepository;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {

    private TeacherRepository repository;
    private TeacherService service;

    @Before
    public void setUp() {
        repository = mock(TeacherRepository.class);
        service = new TeacherServiceImpl(repository);
    }

    @Test
    public void getReport() throws CustomException {
        when(repository.findAll()).thenReturn(getTeachers());
        Assert.assertNotNull(service.getReport());

        try {
            OutputStream out = new FileOutputStream("Teacher_Report.pdf");
            out.write(service.getReport());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();

        Teacher teacher = new Teacher("Lastname", "Firstname", "Middlename");
        teachers.add(teacher);

        return teachers;
    }
}