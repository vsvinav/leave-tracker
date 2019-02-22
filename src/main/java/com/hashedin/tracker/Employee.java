package com.hashedin.tracker;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Month;
import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    private int empId;
    private String empName;
    private String empDesignation;
    private int leaveBalance;
    private String sex;
    private int optionalLeaveBalance=2;
    private LeaveType leaveType;
    private Month month;
    private LocalDate leaveStartDate;
    private LocalDate leaveEndDate;
    private LocalDate joiningDate;
    private int optionalLeavesTaken;
    private int compOffBalance = 0;
    private int leavesTaken;

    public Employee() {}

    public Employee(int empId, String empName, String empDesignation, int leaveBalance, String sex,
                    int optionalLeaveBalance, LeaveType leaveType, LocalDate leaveStartDate,
                    LocalDate leaveEndDate, LocalDate joiningDate, int optionalLeavesTaken,
                    int compOffBalance, int leavesTaken) {
        this.empId = empId;
        this.empName = empName;
        this.empDesignation = empDesignation;
        this.leaveBalance = leaveBalance;
        this.sex = sex;
        this.optionalLeaveBalance = optionalLeaveBalance;
        this.leaveType = leaveType;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.joiningDate = joiningDate;
        this.optionalLeavesTaken = optionalLeavesTaken;
        this.compOffBalance = compOffBalance;
        this.leavesTaken = leavesTaken;
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
    public String getSex() {
        return sex;
    }
    public void reduceLeaveBalance(int numberOfDays) {
        leaveBalance-=numberOfDays;
    }
    public int getLeavesTaken(Month m) {
        return leavesTaken;
    }
    public int getCompOffBalance() {
        return compOffBalance;
    }
    public int getOptionalLeaveBalance() {
        return optionalLeaveBalance;
    }
    public LocalDate getJoiningDate() {
        return joiningDate;
    }
    public int getOptionalLeavesTaken() {  return optionalLeavesTaken; }
    public LeaveType getLeaveType() {   return leaveType;    }
    public LocalDate getLeaveStartDate() {
        return leaveStartDate;
    }
    public LocalDate getLeaveEndDate() {
        return leaveEndDate;
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
    public void setLeavesTaken(int leavesTaken, Month m) {
        this.leavesTaken = leavesTaken;
        this.month = m;
    }
    public void setCompOffBalance(int compOffBalance) {
        this.compOffBalance = compOffBalance;
    }

    public void setOptionalLeaveBalance(int optionalLeaveBalance) {
        this.optionalLeaveBalance = optionalLeaveBalance;
    }
    public void setLeaveEndDate(LocalDate leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }
}
