package com.example.employeees.repository;

import com.example.employeees.entity.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);
}
