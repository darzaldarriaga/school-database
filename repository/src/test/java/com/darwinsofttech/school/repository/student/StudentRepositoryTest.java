package com.darwinsofttech.school.repository.student;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.schedule.ScheduleRepository;
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
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    public void createTest() {
        Student student = studentRepository.save(new Student("Lastname", "Firstname", "M."));
        Assert.assertNotNull(student);
    }

    @Test
    public void updateTest() {
        Student student = studentRepository.save(new Student("Lastname", "Firstname", "M."));
        student.setLastName("Updated Lastname");
        studentRepository.save(student);
        Assert.assertNotNull(student);
    }

    @Test
    public void deleteTest() {
        Student student = studentRepository.save(new Student("Lastname", "Firstname", "M."));
        studentRepository.delete(student);
        Assert.assertTrue(!studentRepository.existsById(student.getId()));
    }

    @Test
    public void findAllTest() {
        Student student = studentRepository.save(new Student("Lastname", "Firstname", "M."));
        List<Student> students = studentRepository.findAll();
        Assert.assertNotNull(students);
        Assert.assertTrue(!students.isEmpty());
    }

    @Test
    public void findByIdTest() {
        Student student = studentRepository.save(new Student("Lastname", "Firstname", "M."));
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        Assert.assertTrue(optionalStudent.isPresent());
        optionalStudent.ifPresent(student1 -> {
            Assert.assertEquals(student1.getLastName(), "Lastname");
        });
    }

    //TODO
    @Test
    public void findAllSchedulesById() {
        Teacher teacher = teacherRepository.save(new Teacher("TeachLast", "TeachFirst", "TeachMiddle"));
        Subject subject = subjectRepository.save(new Subject("IT 101", "Programming"));
        String days = "MWF";
        String time = "7:00 -  10:00";
        Schedule schedule = scheduleRepository.save(new Schedule(subject, teacher, days, time));

        Teacher teacher2 = teacherRepository.save(new Teacher("TeachLast", "TeachFirst", "TeachMiddle"));
        Subject subject2 = subjectRepository.save(new Subject("IT 101", "Programming"));
        String days2 = "TTH";
        String time2 = "10:00 - 12:00";
        Schedule schedule2 = scheduleRepository.save(new Schedule(subject, teacher, days2, time2));

        Student student = studentRepository.save(new Student("StudentLast", "StudentFirst", "StudentMiddle"));
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Student student2 = studentRepository.save(new Student("StudentLast", "StudentFirst", "StudentMiddle"));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(student2);

        schedule.setStudents(studentList);
        schedule2.setStudents(studentList2);

        scheduleRepository.save(schedule);
        scheduleRepository.save(schedule2);

        Student studentWithSchedule = studentRepository.findStudentByIdWithSchedules(student.getId());
        Assert.assertNotNull(studentWithSchedule);
        Assert.assertTrue(studentWithSchedule.getSchedules().contains(schedule));

        Student studentWithSchedule2 = studentRepository.findStudentByIdWithSchedules(student2.getId());
        Assert.assertTrue(studentWithSchedule2.getSchedules().contains(schedule2));


        studentWithSchedule.getSchedules().forEach(schedule1 -> {
            schedule1 = scheduleRepository.findByIdWithStudents(schedule1.getId()).orElseThrow(() -> new IllegalArgumentException("Schedule does not exist"));
            schedule1.getStudents().remove(student);
            scheduleRepository.save(schedule1);
            //studentWithSchedule.getSchedules().remove(schedule1);
        });
        //Assert.assertTrue(studentWithSchedule.getSchedules().isEmpty());

        studentRepository.delete(studentWithSchedule);
        Assert.assertTrue(!studentRepository.existsById(studentWithSchedule.getId()));
    }
}