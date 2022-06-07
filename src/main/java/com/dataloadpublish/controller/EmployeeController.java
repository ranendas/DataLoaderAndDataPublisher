package com.dataloadpublish.controller;

import com.dataloadpublish.entity.Employee;
import com.dataloadpublish.service.EmployeeService;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/api/employee")
    public ResponseEntity<List<Employee>> employees(){
        Iterable<Employee> iterable = employeeService.list();
        List<Employee> list = IterableUtils.toList(iterable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/api/employee/{country}")
    public ResponseEntity<List<Employee>> employeesByCountry(@PathVariable String country){
        logger.info("Country specific data , country name = "+ country);
        Iterable<Employee> iterable = employeeService.list();
        List<Employee> list = IterableUtils.toList(iterable);
        logger.info("Total no of rows = "+ list.size());
        List<Employee> list1 = list.stream().filter(dto->dto.getCountry().equals(country)).collect(Collectors.toList());
        logger.info("Filtered list Total no of rows = "+ list1.size());
        return new ResponseEntity<>(list1, HttpStatus.OK);
    }
}
