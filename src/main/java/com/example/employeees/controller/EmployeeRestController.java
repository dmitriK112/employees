package com.example.employeees.controller;

import com.example.employeees.entity.Employee;
import com.example.employeees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
class EmployeeRestController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id) {
        checkEmployeeExists(id);
        return employeeService.findById(id);
    }

    @PutMapping("/employees/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee e) {
        checkEmployeeExists(id);
        e.setId(id);
        return employeeService.save(e);
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee e) {
        e.setId(null);
        return employeeService.save(e);
    }

    @DeleteMapping("/employees/{id}")
    public String delete(@PathVariable int id) {
        checkEmployeeExists(id);
        employeeService.deleteById(id);
        return "Deleted employee id - " + id;
    }

    private void checkEmployeeExists(int id) {
        if(employeeService.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Employee with id " + id + " not found");
        }
    }
}
