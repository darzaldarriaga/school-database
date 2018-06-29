package com.darwinsofttech.school.service.utils;

import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.service.student.StudentResponseWithoutScheds;
import com.darwinsofttech.school.service.subject.SubjectResponseWithoutScheds;
import com.darwinsofttech.school.service.teacher.TeacherResponseWithoutScheds;

import java.util.ArrayList;
import java.util.List;

public class NoScheduleMapper {

    public static TeacherResponseWithoutScheds mapToTeacherResponse(Teacher teacher) {
        TeacherResponseWithoutScheds teacherResponse = new TeacherResponseWithoutScheds();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setFirstName(teacher.getFirstName());
        teacherResponse.setMiddleName(teacher.getMiddleName());
        return teacherResponse;
    }

    public static SubjectResponseWithoutScheds mapToSubjectResponse(Subject subject) {
        SubjectResponseWithoutScheds subjectResponse = new SubjectResponseWithoutScheds();
        subjectResponse.setId(subject.getId());
        subjectResponse.setSubjectCode(subject.getSubjectCode());
        subjectResponse.setSubjectDescription(subject.getSubjectDescription());
        return subjectResponse;
    }

    public static List<StudentResponseWithoutScheds> mapToStudentResponses(List<Student> students) {
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
}
