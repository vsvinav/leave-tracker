package com.hashedin.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        System.out.println("Hello");
        return employeeService.getEmployee(id);
    }

    @RequestMapping (method = RequestMethod.POST, value = "/employee/create")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @RequestMapping (method = RequestMethod.PUT, value = "/employee/{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
        employeeService.updateEmployee(id,employee);
    }

    @RequestMapping (method = RequestMethod.DELETE, value = "/employee/{id}/delete")
    public void deleteEmployee(@RequestBody Employee employee) {
        employeeService.deleteEmployee(employee);
    }

    @RequestMapping ("/employee/{id}/leavebalance")
    public int employeeLeaveBalance(@PathVariable int id) {
        return employeeService.getEmployeeLeaveBalance(id);
    }

    @RequestMapping ("/employee/{id}/compoffbalance")
    public int employeeCompOffBalance(@PathVariable int id) {
        return employeeService.getEmployeeCompOffBalance(id);
    }
}
