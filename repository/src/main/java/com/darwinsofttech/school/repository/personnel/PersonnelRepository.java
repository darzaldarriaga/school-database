package com.darwinsofttech.school.repository.personnel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {

    List<Personnel> findAllByName(String name);
}
