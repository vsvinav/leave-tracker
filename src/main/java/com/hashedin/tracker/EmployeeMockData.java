package com.hashedin.tracker;


import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeMockData {
    public ArrayList<Employee> getEmployeeList()
    {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee e1 = new Employee(1,"Sam","Developer",2,
                "male",2,LeaveType.general,LocalDate.now(),LocalDate.now().plusDays(1),
                LocalDate.of(2019,01,01),0,0,0);
        Employee e2 = new Employee(2,"Angela","Developer",2,
                "female",2,LeaveType.maternityLeave,LocalDate.now(),LocalDate.now().plusMonths(6),
                LocalDate.of(2018,01,01),0,0,0);
        employeeList.add(e1);
        employeeList.add(e2);

        return employeeList;
    }
    public Employee getEmployeeDetails(int index) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee e1 = new Employee(1,"Sam","Developer",2,
                "male",2,LeaveType.general,LocalDate.now(),LocalDate.now().plusDays(1),
                LocalDate.of(2019,01,01),0,0,0);


        Employee e2 = new Employee(2,"Angela","Developer",2,
                "female",2,LeaveType.maternityLeave,LocalDate.now(),LocalDate.now().plusMonths(6),
                LocalDate.of(2018,01,01),0,0,0);


        Employee e3 = new Employee(3,"Sam","Developer",2,
                "male",2,LeaveType.paternityLeave,LocalDate.now(),LocalDate.now().plusDays(30),
                LocalDate.of(2019,01,01),0,0,0);


        Employee e4 = new Employee(4,"Sam","Developer",2,
                "male",2,LeaveType.maternityLeave,LocalDate.now(),LocalDate.now().plusMonths(6),
                LocalDate.of(2019,01,01),0,0,0);


        Employee e5 = new Employee(5,"Angela","Developer",2,
                "female",2,LeaveType.paternityLeave,LocalDate.now(),LocalDate.now().plusMonths(1),
                LocalDate.of(2019,01,01),0,0,0);
        Employee e6 = new Employee(6,"Angela","Developer",2,
                "female",2,LeaveType.paternityLeave,LocalDate.now().plusMonths(1),LocalDate.now(),
                LocalDate.of(2019,01,01),0,0,0);

        Employee e7 = new Employee(7,"Angela","Developer",2,
                "female",2,LeaveType.paternityLeave,LocalDate.now(),LocalDate.now().plusMonths(1),
                LocalDate.now(),0,0,0);

        Employee e8 = new Employee(8,"Angela","Developer",2,
                "female",2,LeaveType.paternityLeave,LocalDate.now(),LocalDate.now().plusMonths(1),
                LocalDate.of(2019,01,01),0,0,1);

        Employee e9 = new Employee(8,"Angela","Developer",2,
                "female",2,LeaveType.maternityLeave,LocalDate.now(),LocalDate.now().plusMonths(6),
                LocalDate.now(),0,0,1);

        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
        employeeList.add(e5);
        employeeList.add(e6);
        employeeList.add(e7);
        employeeList.add(e8);
        employeeList.add(e9);
        return employeeList.get(index-1);
    }


}
