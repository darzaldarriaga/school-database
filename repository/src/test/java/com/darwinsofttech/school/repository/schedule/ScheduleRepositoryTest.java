package com.darwinsofttech.school.repository.schedule;

import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.student.StudentRepository;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.subject.SubjectRepository;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.repository.teacher.TeacherRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ScheduleRepositoryTest {

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
        Teacher teacher = teacherRepository.save(new Teacher("TeachLast", "TeachFirst", "TeachMiddle"));
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));

        Schedule firstSchedule = scheduleRepository.save(new Schedule(subject, teacher));
        Assert.assertNotNull(firstSchedule);
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(firstSchedule);

        Student student = studentRepository.save(new Student("StudentLast", "StudentFirst", "StudentMiddle", schedules));
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        firstSchedule.setStudents(studentList);
        scheduleRepository.save(firstSchedule);
        Assert.assertNotNull(firstSchedule);
    }

    @Test
    public void updateTest() {

        //creates teacher 1 for initial saving and teacher 2 for update purposes
        Teacher teacher = teacherRepository.save(new Teacher("TeachLast", "TeachFirst", "TeachMiddle"));
        Teacher teacher2 = teacherRepository.save(new Teacher("TeachLast2", "TeachFirst2", "TeachMiddle2"));

        //creates subject 1 for initial saving and subject 2 for update purposes
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));
        Subject subject2 = subjectRepository.save(new Subject("IT 102", "Java Programming"));

        //creates a schedule to be added into a list
        Schedule firstSchedule = scheduleRepository.save(new Schedule(subject, teacher));

        //creates a student to be saved into a list which will be added into the schedule
        Student student = studentRepository.save(new Student("StudentLast", "StudentFirst", "StudentMiddle"));
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        //updates the columns of the schedule created above
        firstSchedule.setStudents(studentList);
        firstSchedule.setSubject(subject2);
        firstSchedule.setTeacher(teacher2);

        scheduleRepository.save(firstSchedule);
        Assert.assertNotNull(firstSchedule);
    }

    @Test
    public void deleteTest() {

        Teacher teacher = teacherRepository.save(new Teacher("TeachLast", "TeachFirst", "TeachMiddle"));
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));

        Schedule firstSchedule = scheduleRepository.save(new Schedule(subject, teacher));

        Student student = studentRepository.save(new Student("StudentLast", "StudentFirst", "StudentMiddle"));
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        firstSchedule.setStudents(studentList);
        scheduleRepository.save(firstSchedule);

        Schedule schedule = scheduleRepository.findByIdWithStudents(firstSchedule.getId()).orElseThrow(() -> new IllegalArgumentException("Schedule does not exist"));
        Assert.assertNotNull(schedule);

        scheduleRepository.delete(schedule);
        Assert.assertTrue(!scheduleRepository.existsById(firstSchedule.getId()));
        Assert.assertTrue(!scheduleRepository.existsById(schedule.getId()));
    }

    @Test
    public void findAllTest() {
        Teacher teacher = teacherRepository.save(new Teacher("TeachLast", "TeachFirst", "TeachMiddle"));
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));

        Schedule schedule = scheduleRepository.save(new Schedule(subject, teacher));
        scheduleRepository.save(schedule);

        List<Schedule> schedules = scheduleRepository.findAll();
        Assert.assertNotNull(schedules);
        Assert.assertTrue(!schedules.isEmpty());
    }

    @Test
    public void findByIdTest() {
        Teacher teacher = teacherRepository.save(new Teacher("TeachLast", "TeachFirst", "TeachMiddle"));
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));

        Schedule schedule = scheduleRepository.save(new Schedule(subject, teacher));
        scheduleRepository.save(schedule);

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(schedule.getId());
        Assert.assertTrue(optionalSchedule.isPresent());
        optionalSchedule.ifPresent(schedule1 -> {
            Assert.assertEquals(teacher, schedule1.getTeacher());
        });
    }

    @Test
    public void assignStudentToScheduleTest() {

        Teacher teacher = teacherRepository.save(new Teacher("TeachLast", "TeachFirst", "TeachMiddle"));
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));
        Schedule schedule = scheduleRepository.save(new Schedule(subject, teacher));

        Student student = studentRepository.save(new Student("StudentLast", "StudentFirst", "StudentMiddle"));
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        schedule.setStudents(studentList);
        scheduleRepository.save(schedule);

        Schedule scheduleById = scheduleRepository.findByIdWithStudents(schedule.getId()).orElseThrow(() -> new IllegalArgumentException("Schedule does not exist"));
        Assert.assertTrue(scheduleById.getStudents().contains(student));
    }
}