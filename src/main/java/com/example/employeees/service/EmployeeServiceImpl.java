package com.example.employeees.service;

import com.example.employeees.entity.Employee;
import com.example.employeees.exception.EmailAlreadyExistsException;
import com.example.employeees.exception.ObjectNotFoundException;
import com.example.employeees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException("Employee", id));
    }

    @Transactional
    @Override
    public Employee save(Employee e) {
        if(e.getId()==null){
            if(employeeRepository.existsByEmail(e.getEmail())){
                throw new EmailAlreadyExistsException(e.getEmail());
            }
        }else{
            Employee existingWithEmail = employeeRepository.findByEmail(e.getEmail()).orElse(null);
            if(existingWithEmail!=null && !Objects.equals(e.getId(), existingWithEmail.getId())){
                throw new EmailAlreadyExistsException(e.getEmail());
            }
        }
        return employeeRepository.save(e);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        if(!employeeRepository.existsById(id)){
            throw new ObjectNotFoundException("Employee", id);
        }
        employeeRepository.deleteById(id);
    }
}
