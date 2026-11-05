package com.example.employees_website.service;

import com.example.employees_website.entity.Employee;
import com.example.employees_website.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository EmployeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    public List<Employee> findAll() {
        return EmployeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return EmployeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Transactional
    public Employee save(Employee Employee) {
        return EmployeeRepository.save(Employee);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!EmployeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        EmployeeRepository.deleteById(id);
    }
}
