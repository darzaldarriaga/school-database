package com.darwinsofttech.school.repository.teacher;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.schedule.ScheduleRepository;
import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.student.StudentRepository;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.subject.SubjectRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void createTest() {
        Teacher teacher = teacherRepository.save(new Teacher("Lastteacher", "Firstteacher", "Middleteacher"));
        Assert.assertNotNull(teacher);
    }

    @Test
    public void updateTest() {
        Teacher teacher = teacherRepository.save(new Teacher("Lastteacher", "Firstteacher", "Middleteacher"));
        teacher.setLastName("Updated Lastteacher");
        teacherRepository.save(teacher);
        Assert.assertNotNull(teacher);
    }

    @Test
    public void deleteTest() {
        Teacher teacher = teacherRepository.save(new Teacher("Lastteacher", "Firstteacher", "Middleteacher"));
        teacherRepository.delete(teacher);
        Assert.assertTrue(!teacherRepository.existsById(teacher.getId()));
    }

    @Test
    public void findAllTest() {
        Teacher teacher = teacherRepository.save(new Teacher("Lastteacher", "Firstteacher", "Middleteacher"));
        List<Teacher> teachers = teacherRepository.findAll();
        Assert.assertNotNull(teacher);
        Assert.assertTrue(!teachers.isEmpty());
    }

    @Test
    public void findByIdTest() {
        Teacher teacher = teacherRepository.save(new Teacher("Lastteacher", "Firstteacher", "Middleteacher"));
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacher.getId());
        Assert.assertTrue(optionalTeacher.isPresent());
        optionalTeacher.ifPresent(teacher1 -> {
            Assert.assertEquals("Lastteacher", teacher1.getLastName());
        });
    }

    @Test
    public void findByIdWithSubjectsTest() {
        Teacher teacher = teacherRepository.save(new Teacher("TeacherLast", "TeacherFirst", "TeacherMiddle"));

        Subject subject1 = subjectRepository.save(new Subject("IT 101", "OOP"));
        Subject subject2 = subjectRepository.save(new Subject("IT 102", "Data Structure"));
        Subject subject3 = subjectRepository.save(new Subject("IT 103", "Database"));

        Student student1 = studentRepository.save(new Student("Zaldarriaga", "Darwin", "Dedel"));
        Student student2 = studentRepository.save(new Student("Aguiling", "Stefanie", ""));
        Student student3 = studentRepository.save(new Student("Wuthrich", "Wayne", ""));
        Student student4 = studentRepository.save(new Student("Doroteo", "Malou", "Coronado"));
        Student student5 = studentRepository.save(new Student("Resano", "Jivan", ""));

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        Schedule schedule1 = scheduleRepository.save(new Schedule(subject1, teacher, students));
        Schedule schedule2 = scheduleRepository.save(new Schedule(subject2, teacher, students));
        Schedule schedule3 = scheduleRepository.save(new Schedule(subject3, teacher, students));

        Teacher findTeacher = teacherRepository.findTeacherByIdWithSchedules(teacher.getId());
        Assert.assertNotNull(findTeacher);
        Assert.assertTrue(findTeacher.getSchedules().contains(schedule1));
        Assert.assertTrue(findTeacher.getSchedules().contains(schedule2));
        Assert.assertTrue(findTeacher.getSchedules().contains(schedule3));
    }

    @Test
    public void deleteTeacherWithSchedulesTest() {
        findByIdWithSubjectsTest();
        Teacher teacher = teacherRepository.findTeacherByIdWithSchedules(1);
        teacherRepository.delete(teacher);
        Assert.assertTrue(!teacherRepository.existsById(teacher.getId()));
    }
}