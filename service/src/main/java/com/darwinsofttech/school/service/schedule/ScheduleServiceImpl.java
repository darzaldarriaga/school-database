package com.darwinsofttech.school.service.schedule;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.schedule.ScheduleRepository;
import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.student.StudentRepository;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.subject.SubjectRepository;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.repository.teacher.TeacherRepository;
import com.darwinsofttech.school.service.utils.NoScheduleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    //TODO add repositories
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.scheduleRepository = scheduleRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void create(ScheduleRequest scheduleRequest) {
        Subject subject = subjectRepository.findById(scheduleRequest.getSubjectId()).orElseThrow(
                () -> new IllegalArgumentException("Subject does not exist")
        );

        Teacher teacher = teacherRepository.findById(scheduleRequest.getTeacherId()).orElseThrow(
                () -> new IllegalArgumentException("Teacher does not exist")
        );

        Schedule schedule = new Schedule(subject, teacher, scheduleRequest.getDays(), scheduleRequest.getTime());
        scheduleRepository.save(schedule);
    }

    @Override
    public void update(ScheduleRequest scheduleRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleRequest.getId()).orElseThrow(() -> new IllegalArgumentException("Schedule does not exist"));
        Subject subject = subjectRepository.findById(scheduleRequest.getSubjectId()).orElseThrow(() -> new IllegalArgumentException("Subject does not exist"));
        Teacher teacher = teacherRepository.findById(scheduleRequest.getTeacherId()).orElseThrow(() -> new IllegalArgumentException("Teacher does not exist"));

        schedule.setSubject(subject);
        schedule.setTeacher(teacher);
        schedule.setDays(scheduleRequest.getDays());
        schedule.setTime(scheduleRequest.getTime());
        scheduleRepository.save(schedule);
    }

    @Override
    public void addStudent(ScheduleRequest scheduleRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleRequest.getId()).orElseThrow(() -> new IllegalArgumentException("Schedule does not exist"));
        Student student = studentRepository.findById(scheduleRequest.getStudentId()).orElseThrow(() -> new IllegalArgumentException("Student does not exist"));

        schedule.getStudents().add(student);
        scheduleRepository.save(schedule);
    }

    @Override
    public void delete(int scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    @Override
    public List<ScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(schedule -> {
            ScheduleResponse scheduleResponse = mapToScheduleResponse(schedule);
            return scheduleResponse;
        }).collect(Collectors.toList());
    }

    public ScheduleResponse mapToScheduleResponse(Schedule schedule) {
        ScheduleResponse scheduleResponse = new ScheduleResponse(
                schedule.getId(),
                NoScheduleMapper.mapToSubjectResponse(schedule.getSubject()),
                NoScheduleMapper.mapToTeacherResponse(schedule.getTeacher()),
                schedule.getDays(),
                schedule.getTime(),
                NoScheduleMapper.mapToStudentResponses(schedule.getStudents())
        );
        return  scheduleResponse;
    }

    @Override
    public ScheduleResponse findById(int scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule does not exist"));
        ScheduleResponse scheduleResponse = mapToScheduleResponse(schedule);
        return scheduleResponse;
    }
}
