package com.darwinsofttech.school.repository.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("SELECT s FROM Schedule s JOIN FETCH s.students WHERE s.id = ?1")
    Optional<Schedule> findByIdWithStudents(int id);
}
