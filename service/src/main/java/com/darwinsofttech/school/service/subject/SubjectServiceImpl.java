package com.darwinsofttech.school.service.subject;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.subject.SubjectRepository;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.service.schedule.SubjectScheduleResponse;
import com.darwinsofttech.school.service.student.StudentResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void save(String subjectCode, String subjectDescription) {
        Subject subject = new Subject();
        subjectRepository.save(subject);
    }

    @Override
    public void update(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void remove(int subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    @Override
    public List<SubjectResponse> findAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(subject -> {
                    SubjectResponse subjectResponse = mapToSubjectResponse(subject);
                    subjectResponse.setSchedules(mapToScheduleResponses(subject.getSchedules()));
                    return subjectResponse;
                }).collect(Collectors.toList());
    }

    private SubjectResponse mapToSubjectResponse(Subject subject) {
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(subject.getId());
        subjectResponse.setSubjectCode(subject.getSubjectCode());
        subjectResponse.setSubjectDescription(subject.getSubjectDescription());
        return subjectResponse;
    }

    private List<SubjectScheduleResponse> mapToScheduleResponses(List<Schedule> schedules) {
        List<SubjectScheduleResponse> subjectScheduleResponses = new ArrayList<>();
        schedules.forEach(schedule -> {
            SubjectScheduleResponse subjectScheduleResponse = new SubjectScheduleResponse();
            subjectScheduleResponse.setId(schedule.getId());
            subjectScheduleResponse.setTeacher(mapToTeacherResponse(schedule.getTeacher()));
            subjectScheduleResponse.setStudents(mapToStudentResponses(schedule.getStudents()));
            subjectScheduleResponses.add(subjectScheduleResponse);
        });
        return subjectScheduleResponses;
    }

    private TeacherResponseWithoutScheds mapToTeacherResponse(Teacher teacher) {
        TeacherResponseWithoutScheds teacherResponse = new TeacherResponseWithoutScheds();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setFirstName(teacher.getFirstName());
        teacherResponse.setMiddleName(teacher.getMiddleName());
        return teacherResponse;
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
    public Subject findById(int subjectId) {
        return subjectRepository.findById(subjectId).get();
    }
}
