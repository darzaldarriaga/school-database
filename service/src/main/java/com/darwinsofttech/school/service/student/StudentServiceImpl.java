package com.darwinsofttech.school.service.student;

import com.darwinsofttech.school.repository.schedule.Schedule;
import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.student.StudentRepository;
import com.darwinsofttech.school.service.Conversion;
import com.darwinsofttech.school.service.exceptions.CustomException;
import com.darwinsofttech.school.service.schedule.StudentScheduleResponse;
import com.darwinsofttech.school.service.utils.NoScheduleMapper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(String lastName, String firstName, String middleName) {
        Student student = new Student();
        student.setLastName(Conversion.convertNames(lastName));
        student.setFirstName(Conversion.convertNames(firstName));
        student.setMiddleName(Conversion.convertNames(middleName));
        return studentRepository.save(student);
    }

    @Override
    public void update(StudentRequest studentRequest) {
        Optional<Student> studentOptional = studentRepository.findById(studentRequest.getId());
        if(studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setLastName(studentRequest.getLastName());
            student.setFirstName(studentRequest.getFirstName());
            student.setMiddleName(studentRequest.getMiddleName());
            studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Student does not exist");
        }
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
                    studentResponse.setSchedules(mapToScheduleResponses(student.getSchedules()));
                    return studentResponse;
                })
                .collect(Collectors.toList());
    }

    private StudentResponse mapToStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse(student.getId(), student.getLastName(), student.getFirstName(), student.getMiddleName());
        return studentResponse;
    }

    private List<StudentScheduleResponse> mapToScheduleResponses(List<Schedule> schedules) {
        List<StudentScheduleResponse> studentScheduleResponses = new ArrayList<>();
        schedules.forEach(schedule -> {
            StudentScheduleResponse studentScheduleResponse = new StudentScheduleResponse();
            studentScheduleResponse.setId(schedule.getId());
            studentScheduleResponse.setSubject(NoScheduleMapper.mapToSubjectResponse(schedule.getSubject()));
            studentScheduleResponse.setTeacher(NoScheduleMapper.mapToTeacherResponse(schedule.getTeacher()));
            studentScheduleResponse.setDays(schedule.getDays());
            studentScheduleResponse.setTime(schedule.getTime());
            studentScheduleResponses.add(studentScheduleResponse);
        });
        return studentScheduleResponses;
    }

    @Override
    public StudentResponse findById(int studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student Id not found"));
        StudentResponse studentResponse = mapToStudentResponse(student);
        studentResponse.setSchedules(mapToScheduleResponses(student.getSchedules()));
        return studentResponse;
    }

    @Override
    public byte[] getReport() throws CustomException {
        List<Student> students = studentRepository.findAll();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);
        try {
            byte[] pdfReportInBytes = JasperRunManager.runReportToPdf("..\\reports\\student_report.jasper", null, dataSource);
            return pdfReportInBytes;
        } catch (JRException e) {
            e.printStackTrace();
            throw new CustomException("Report was not generated");
        }
    }
}
