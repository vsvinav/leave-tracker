package com.hashedin.tracker;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class EmployeeController {

    @RequestMapping("/employees")
    public ArrayList<Employee> getAllEmployees() {
        return new EmployeeMockData().getEmployeeList();
    }

    @RequestMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        System.out.println("Hello");
        return new EmployeeMockData().getEmployeeDetails(id);
    }
}
