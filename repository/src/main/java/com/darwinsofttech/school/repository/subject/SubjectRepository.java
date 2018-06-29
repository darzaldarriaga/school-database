package com.darwinsofttech.school.repository.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("SELECT s FROM Subject s JOIN FETCH s.schedules WHERE s.id = ?1")
    Subject findByIdWithSchedules(int id);
}
