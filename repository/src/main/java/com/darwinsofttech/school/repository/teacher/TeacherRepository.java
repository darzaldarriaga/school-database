package com.darwinsofttech.school.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("SELECT t FROM Teacher t JOIN FETCH t.schedules WHERE t.id = ?1")
    Teacher findTeacherByIdWithSchedules(int id);
}
