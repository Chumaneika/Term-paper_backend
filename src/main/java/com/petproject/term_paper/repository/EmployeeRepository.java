package com.petproject.term_paper.repository;

import com.petproject.term_paper.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByName(String name);
}
