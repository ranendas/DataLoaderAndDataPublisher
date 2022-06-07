package com.dataloadpublish.service;

import com.dataloadpublish.dao.EmployeeRepository;
import com.dataloadpublish.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component//TODO- @component vs @Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> list() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    public void save(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }
}
