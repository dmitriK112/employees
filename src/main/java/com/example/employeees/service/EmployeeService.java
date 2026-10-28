package com.example.employeees.service;

import com.example.employeees.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee e);
    void deleteById(int id);

}
