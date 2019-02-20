package com.hashedin.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMockData {
    public ArrayList<Employee> getEmployeeList()
    {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee e1 = new Employee(1,"Sam","Developer",0,false,"male",false,LocalDate.now(), LocalDate.of(2019,01,01),0,0);
        Employee e2 = new Employee(2,"Olivia","Developer",0,true,"female",false,LocalDate.now(), LocalDate.of(2019,01,01),0,0);
        employeeList.add(e1);
        employeeList.add(e2);
        return employeeList;
    }
    public Employee getEmployeeDetails(int index) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee e1 = new Employee(1,"Sam","Developer",8,false,"male",false,LocalDate.now(), LocalDate.of(2019,01,01),0,0);
        Employee e2 = new Employee(2,"Olivia","Developer",0,true,"female",false,LocalDate.now(), LocalDate.of(2018,01,01),0,0);
        employeeList.add(e1);
        employeeList.add(e2);
        return employeeList.get(index-1);
    }


}
