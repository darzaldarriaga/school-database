package com.darwinsofttech.school.service.subject;

import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.subject.SubjectRepository;
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
public class SubjectServiceTest {

    private SubjectService subjectService;
    private SubjectRepository subjectRepository;

    @Before
    public void setUp() {
        subjectRepository = mock(SubjectRepository.class);
        subjectService = new SubjectServiceImpl(subjectRepository);
    }

    @Test
    public void getReport() throws CustomException {
        when(subjectRepository.findAll()).thenReturn(getSubjects());

        Assert.assertNotNull(subjectService.getReport());
        try {
            OutputStream out = new FileOutputStream("Subject_Report.pdf");
            out.write(subjectService.getReport());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Subject> getSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Subject subject = new Subject("IT 101", "Basic Programming");
        subjects.add(subject);
        Subject subject2 = new Subject("IT 102", "Advanced Programming");
        subjects.add(subject2);
        Subject subject3 = new Subject("IT 102", "Java Programming");
        subjects.add(subject3);
        return subjects;
    }
}