package com.darwinsofttech.school.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s JOIN FETCH s.schedules WHERE s.id = ?1")
    Student findStudentByIdWithSchedules(int id);
}
