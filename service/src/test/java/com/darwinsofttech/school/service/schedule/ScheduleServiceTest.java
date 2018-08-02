package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.schedule.ScheduleRepository;
import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.student.StudentRepository;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.subject.SubjectRepository;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.repository.teacher.TeacherRepository;
import com.darwinsofttech.school.service.exceptions.CustomException;
import com.darwinsofttech.school.service.student.StudentResponseWithoutScheds;
import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;
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
public class ScheduleServiceTest {

    private ScheduleRepository scheduleRepository;
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;
    private ScheduleService service;

    @Before
    public void setUp() {
        scheduleRepository = mock(ScheduleRepository.class);
        subjectRepository = mock(SubjectRepository.class);
        teacherRepository = mock(TeacherRepository.class);
        studentRepository = mock(StudentRepository.class);
        service = new ScheduleServiceImpl(scheduleRepository, subjectRepository, teacherRepository, studentRepository);
    }

    @Test
    public void getReport() throws CustomException {
        when(scheduleRepository.findAll()).thenReturn(getSchedules());
        Assert.assertNotNull(service.getReport());

        try {
            OutputStream out = new FileOutputStream("Schedule_Reports.pdf");
            out.write(service.getReport());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Schedule> getSchedules() {
        List<Schedule> schedules = new ArrayList<>();

        Student student = new Student("StudentL", "StudentF", "StudentM");
        List<Student> students = new ArrayList<>();
        students.add(student);

        Schedule schedule = new Schedule(new Subject("IT 101", "Programming"),
                new Teacher("TeacherL", "TeacherF", "TeacherM"),
                "M",
                "07:00 - 10:00",
                students);
        schedules.add(schedule);
        return schedules;
    }

}