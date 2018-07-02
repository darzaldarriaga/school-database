package com.darwinsofttech.school.service.subject;

import com.darwinsofttech.school.repository.subject.Subject;

import java.util.List;

public interface SubjectService {
    void save(String subjectCode, String subjectDescription);
    void update(SubjectRequest subjectRequest);
    void remove(int subjectId);
    List<SubjectResponse> findAll();
    SubjectResponse findById(int subjectId);
}
