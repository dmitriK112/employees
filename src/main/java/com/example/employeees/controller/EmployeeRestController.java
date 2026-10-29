package com.example.employeees.controller;

import com.example.employeees.entity.Employee;
import com.example.employeees.response.ResponseFactory;
import com.example.employeees.response.ResponseWrapper;
import com.example.employeees.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
class EmployeeRestController {
    private final EmployeeService employeeService;
    private final ResponseFactory responseFactory;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ResponseFactory responseFactory) {
        this.employeeService = employeeService;
        this.responseFactory = responseFactory;
    }

    @GetMapping("/employees")
    public ResponseEntity<ResponseWrapper<List<Employee>>> findAll() {
        return responseFactory.ok("List of employees", employeeService.findAll());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> findById(@PathVariable int id) {
        return responseFactory.ok("The employee", employeeService.findById(id));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> update(@PathVariable int id, @Valid @RequestBody Employee e) {
        employeeService.findById(id);
        e.setId(id);
        Employee updatedEmployee = employeeService.save(e);
        return responseFactory.ok("The employee was updated", updatedEmployee);
    }

    @PostMapping("/employees")
    public ResponseEntity<ResponseWrapper<Employee>> save(@Valid @RequestBody Employee e) {
        e.setId(null);
        return responseFactory.created("The employee was created", employeeService.save(e));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Integer>> delete(@PathVariable int id) {
        employeeService.deleteById(id);
        return responseFactory.ok("The employee was deleted", id);
    }

}
