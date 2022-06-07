package com.dataloadpublish;

import com.dataloadpublish.entity.Employee;
import com.dataloadpublish.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DataLoaderAndDataPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataLoaderAndDataPublisherApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(EmployeeService employeeService){
        return args -> {
            // read JSON and load json
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Employee>> typeReference = new TypeReference<List<Employee>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/json-50000.json");
            try {
                List<Employee> employees = mapper.readValue(inputStream,typeReference);
                if(!employeeService.list().iterator().hasNext()) {
                    employeeService.save(employees);
                }
                System.out.println("Employee Saved!");
            } catch (IOException e){
                System.out.println("Unable to save Employee: " + e.getMessage());
            }
        };
    }

}
