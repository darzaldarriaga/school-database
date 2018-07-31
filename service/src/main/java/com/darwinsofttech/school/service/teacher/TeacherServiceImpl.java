package com.darwinsofttech.school.service.teacher;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.repository.teacher.TeacherRepository;
import com.darwinsofttech.school.service.Conversion;
import com.darwinsofttech.school.service.schedule.TeacherScheduleResponse;
import com.darwinsofttech.school.service.utils.NoScheduleMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void update(TeacherRequest teacherRequest) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherRequest.getId());
        if(optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setLastName(teacherRequest.getLastName());
            teacher.setFirstName(teacherRequest.getFirstName());
            teacher.setMiddleName(teacherRequest.getMiddleName());
            teacherRepository.save(teacher);
        } else {
            throw new IllegalArgumentException("Teacher does not exist");
        }
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
        TeacherResponse teacherResponse = new TeacherResponse(teacher.getId(), teacher.getLastName(), teacher.getFirstName(), teacher.getMiddleName());
        return teacherResponse;
    }

    private List<TeacherScheduleResponse> mapToScheduleResponse(List<Schedule> schedules) {
        List<TeacherScheduleResponse> teacherScheduleResponses = new ArrayList<>();
        schedules.forEach(schedule -> {
            TeacherScheduleResponse teacherScheduleResponse = new TeacherScheduleResponse();
            teacherScheduleResponse.setId(schedule.getId());
            teacherScheduleResponse.setSubject(NoScheduleMapper.mapToSubjectResponse(schedule.getSubject()));
            teacherScheduleResponse.setStudents(NoScheduleMapper.mapToStudentResponses(schedule.getStudents()));
            teacherScheduleResponse.setDays(schedule.getDays());
            teacherScheduleResponse.setTime(schedule.getTime());
            teacherScheduleResponses.add(teacherScheduleResponse);
        });
        return teacherScheduleResponses;
    }

    @Override
    public TeacherResponse findById(int teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalArgumentException("Teacher Id not found"));
        TeacherResponse teacherResponse = mapToTeacherResponse(teacher);
        teacherResponse.setSchedules(mapToScheduleResponse(teacher.getSchedules()));
        return teacherResponse;
    }
}
