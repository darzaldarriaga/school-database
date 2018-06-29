package com.darwinsofttech.school.repository.subject;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.schedule.ScheduleRepository;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.repository.teacher.TeacherRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    public void createTest() {
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));
        Assert.assertNotNull(subject);
    }

    @Test
    public void updateTest() {
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));
        subject.setSubjectDescription("Updated Description");
        subjectRepository.save(subject);
        Assert.assertNotNull(subject);
    }

    @Test
    public void deleteTest() {
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));
        subjectRepository.delete(subject);
        Assert.assertTrue(!subjectRepository.existsById(subject.getId()));
    }

    @Test
    public void findAllTest() {
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));
        List<Subject> subjects = subjectRepository.findAll();
        Assert.assertNotNull(subjects);
        Assert.assertTrue(!subjects.isEmpty());
    }

    @Test
    public void findByIdTest() {
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));
        Optional<Subject> optionalSubject = subjectRepository.findById(subject.getId());
        Assert.assertTrue(optionalSubject.isPresent());
        optionalSubject.ifPresent(subject1 -> {
            Assert.assertEquals("Programming", subject1.getSubjectDescription());
        });
    }

    @Test
    public void findByIdWithSchedulesTest() {
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));

        Teacher teacher1 = teacherRepository.save(new Teacher("Last1", "First1", "Middle1"));
        Teacher teacher2 = teacherRepository.save(new Teacher("Last2", "First2", "Middle2"));
        Teacher teacher3 = teacherRepository.save(new Teacher("Last3", "First3", "Middle3"));

        Schedule schedule1 = scheduleRepository.save(new Schedule(subject, teacher1));
        Schedule schedule2 = scheduleRepository.save(new Schedule(subject, teacher2));
        Schedule schedule3 = scheduleRepository.save(new Schedule(subject, teacher3));

        Subject findSubject = subjectRepository.findByIdWithSchedules(subject.getId());
        Assert.assertNotNull(findSubject);
        Assert.assertTrue(findSubject.getSchedules().contains(schedule1));
        Assert.assertTrue(findSubject.getSchedules().contains(schedule2));
        Assert.assertTrue(findSubject.getSchedules().contains(schedule3));
    }

    @Test
    public void deleteSubjectWithSchedulesTest() {
        findByIdWithSchedulesTest();
        Subject subject = subjectRepository.findByIdWithSchedules(1);
        subjectRepository.delete(subject);
        Assert.assertNotNull(subjectRepository.existsById(subject.getId()));
    }
}