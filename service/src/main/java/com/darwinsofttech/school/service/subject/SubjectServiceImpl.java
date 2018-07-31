package com.darwinsofttech.school.service.subject;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.subject.SubjectRepository;
import com.darwinsofttech.school.service.schedule.SubjectScheduleResponse;
import com.darwinsofttech.school.service.utils.NoScheduleMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        subject.setSubjectCode(subjectCode);
        subject.setSubjectDescription(subjectDescription);
        subjectRepository.save(subject);
    }

    @Override
    public void update(SubjectRequest subjectRequest) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectRequest.getId());
        if(optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            subject.setSubjectCode(subjectRequest.getSubjectCode());
            subject.setSubjectDescription(subjectRequest.getSubjectDescription());
            subjectRepository.save(subject);
        } else {
            throw new IllegalArgumentException("Subject does not exist");
        }
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
        SubjectResponse subjectResponse = new SubjectResponse(subject.getId(), subject.getSubjectCode(), subject.getSubjectDescription());
        return subjectResponse;
    }

    private List<SubjectScheduleResponse> mapToScheduleResponses(List<Schedule> schedules) {
        List<SubjectScheduleResponse> subjectScheduleResponses = new ArrayList<>();
        schedules.forEach(schedule -> {
            SubjectScheduleResponse subjectScheduleResponse = new SubjectScheduleResponse();
            subjectScheduleResponse.setId(schedule.getId());
            subjectScheduleResponse.setTeacher(NoScheduleMapper.mapToTeacherResponse(schedule.getTeacher()));
            subjectScheduleResponse.setStudents(NoScheduleMapper.mapToStudentResponses(schedule.getStudents()));
            subjectScheduleResponse.setDays(schedule.getDays());
            subjectScheduleResponse.setTime(schedule.getTime());
            subjectScheduleResponses.add(subjectScheduleResponse);
        });
        return subjectScheduleResponses;
    }

    @Override
    public SubjectResponse findById(int subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("Subject Id not found"));
        SubjectResponse subjectResponse = mapToSubjectResponse(subject);
        subjectResponse.setSchedules(mapToScheduleResponses(subject.getSchedules()));
        return subjectResponse;
    }
}
