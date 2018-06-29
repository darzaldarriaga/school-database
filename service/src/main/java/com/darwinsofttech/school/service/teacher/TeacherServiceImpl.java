package com.darwinsofttech.school.service.teacher;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.repository.teacher.TeacherRepository;
import com.darwinsofttech.school.service.Conversion;
import com.darwinsofttech.school.service.schedule.TeacherScheduleResponse;
import com.darwinsofttech.school.service.student.StudentResponse;
import com.darwinsofttech.school.service.student.StudentResponseWithoutScheds;
import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void save(String lastName, String firstName, String middleName) {
        Teacher teacher = new Teacher();
        teacher.setLastName(Conversion.convertNames(lastName));
        teacher.setFirstName(Conversion.convertNames(firstName));
        teacher.setMiddleName(Conversion.convertNames(middleName));
        teacherRepository.save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void remove(int teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public List<TeacherResponse> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(teacher -> {
                    TeacherResponse teacherResponse = mapToTeacherResponse(teacher);
                    teacherResponse.setSchedules(mapToScheduleResponse(teacher.getSchedules()));
                    return teacherResponse;
                })
                .collect(Collectors.toList());
    }

    private TeacherResponse mapToTeacherResponse(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setFirstName(teacher.getFirstName());
        teacherResponse.setMiddleName(teacher.getMiddleName());
        teacherResponse.setSchedules(mapToScheduleResponse(teacher.getSchedules()));
        return teacherResponse;
    }

    private List<TeacherScheduleResponse> mapToScheduleResponse(List<Schedule> schedules) {
        List<TeacherScheduleResponse> teacherScheduleResponses = new ArrayList<>();
        schedules.forEach(schedule -> {
            TeacherScheduleResponse teacherScheduleResponse = new TeacherScheduleResponse();
            teacherScheduleResponse.setId(schedule.getId());
            teacherScheduleResponse.setSubject(mapToSubjectResponse(schedule.getSubject()));
            teacherScheduleResponse.setStudents(mapToStudentResponses(schedule.getStudents()));
            teacherScheduleResponses.add(teacherScheduleResponse);
        });
        return teacherScheduleResponses;
    }

    private SubjectResponseWithoutScheds mapToSubjectResponse(Subject subject) {
        SubjectResponseWithoutScheds subjectResponse = new SubjectResponseWithoutScheds();
        subjectResponse.setId(subject.getId());
        subjectResponse.setSubjectCode(subject.getSubjectCode());
        subjectResponse.setSubjectDescription(subject.getSubjectDescription());
        return subjectResponse;
    }

    private List<StudentResponseWithoutScheds> mapToStudentResponses(List<Student> students) {
        List<StudentResponseWithoutScheds> studentResponses = new ArrayList<>();
        students.forEach(student -> {
            StudentResponseWithoutScheds studentResponse = new StudentResponseWithoutScheds();
            studentResponse.setId(student.getId());
            studentResponse.setLastName(student.getLastName());
            studentResponse.setFirstName(student.getFirstName());
            studentResponse.setMiddleName(student.getMiddleName());
            studentResponses.add(studentResponse);
        });
        return studentResponses;
    }

    @Override
    public Teacher findById(int teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalArgumentException("Teacher ID not found"));
    }
}
