package com.darwinsofttech.school.service.student;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.student.StudentRepository;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.service.Conversion;
import com.darwinsofttech.school.service.schedule.StudentScheduleResponse;
import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void save(String lastName, String firstName, String middleName) {
        Student student = new Student();
        student.setLastName(Conversion.convertNames(lastName));
        student.setFirstName(Conversion.convertNames(firstName));
        student.setMiddleName(Conversion.convertNames(middleName));
        studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(int studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentResponse> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> {
                    StudentResponse studentResponse = mapToStudentResponse(student);
                    studentResponse.setSchedules(mapToScheduleResponse(student.getSchedules()));
                    return studentResponse;
                })
                .collect(Collectors.toList());
    }

    private StudentResponse mapToStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setMiddleName(student.getMiddleName());
        return studentResponse;
    }

    private List<StudentScheduleResponse> mapToScheduleResponse(List<Schedule> schedules) {
        List<StudentScheduleResponse> studentScheduleResponses = new ArrayList<>();
        schedules.forEach(schedule -> {
            StudentScheduleResponse studentScheduleResponse = new StudentScheduleResponse();
            studentScheduleResponse.setSubject(mapToSubjectResponse(schedule.getSubject()));
            studentScheduleResponse.setTeacher(mapToTeacherResponse(schedule.getTeacher()));
            studentScheduleResponses.add(studentScheduleResponse);
        });
        return studentScheduleResponses;
    }

    private SubjectResponseWithoutScheds mapToSubjectResponse(Subject subject) {
        SubjectResponseWithoutScheds subjectResponse = new SubjectResponseWithoutScheds();
        subjectResponse.setId(subject.getId());
        subjectResponse.setSubjectCode(subject.getSubjectCode());
        subjectResponse.setSubjectDescription(subject.getSubjectDescription());
        return subjectResponse;
    }

    private TeacherResponseWithoutScheds mapToTeacherResponse(Teacher teacher) {
        TeacherResponseWithoutScheds teacherResponse = new TeacherResponseWithoutScheds();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setFirstName(teacher.getFirstName());
        teacherResponse.setMiddleName(teacher.getMiddleName());
        return teacherResponse;
    }

    @Override
    public Student findById(int studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student Id not found"));
    }
}
