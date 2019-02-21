package com.hashedin.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    public Employee getEmployee(int index) {
        Optional<Employee> emp = employeeRepository.findById(index);
        return emp.get();
//        return employeeRepository.findById(index);
    }

    public void updateEmployee (int id, Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee (Employee employee) {
        employeeRepository.delete(employee);
    }

    public int getEmployeeLeaveBalance (int index) {
        Optional<Employee> emp = employeeRepository.findById(index);
        return emp.get().getLeaveBalance();
    }
    public int getEmployeeCompOffBalance (int index) {
        Optional<Employee> emp = employeeRepository.findById(index);
        return emp.get().getCompOffBalance();
    }

}
