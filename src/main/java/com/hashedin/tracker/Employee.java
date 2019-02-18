package com.hashedin.tracker;
import java.time.temporal.ChronoUnit;
import java.time.Period;
import java.time.LocalDate;
import java.util.Date;

public class Employee {

    private int empId;
    private String empName;
    private String empDesignation;
    private int leaveBalance=5;

    public int getLeavesTaken() {
        return leavesTaken;
    }

    public void setLeavesTaken(int leavesTaken) {
        this.leavesTaken = leavesTaken;
    }

    private int leavesTaken;
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String sex;
    Employee() {

    }
    Employee(int eId, String eName, String eDesignation, int leaveBal, String sex) {
        this.empId = eId;
        this.empName = eName;
        this.empDesignation = eDesignation;
        this.leaveBalance = leaveBal;
        this.sex = sex;
    }
    // Getter Methods
    public int getEmpId() {
        return empId;
    }
    public String getEmpName() {
        return empName;
    }
    public String getEmpDesignation() {
        return empDesignation;
    }
    public int getLeaveBalance() {
        return leaveBalance;
    }
    public void reduceLeaveBalance(int numberOfDays) {
        leaveBalance-=numberOfDays;
    }

    // Setter Methods
    public void setEmpId(int id) {
        this.empId = id;
    }
    public void setEmpName(String name) {
        this.empName = name;
    }
    public void setEmpDesignation(String designation) {
        this.empDesignation = designation;
    }
    public void setLeaveBalance(int lb) {
        this.leaveBalance = lb;
    }
}
