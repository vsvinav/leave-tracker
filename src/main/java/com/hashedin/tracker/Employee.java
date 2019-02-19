package com.hashedin.tracker;

import java.time.Month;
import java.time.LocalDate;

public class Employee {

    private int empId;
    private String empName;
    private String empDesignation;
    private int leaveBalance;
    private boolean maternityLeaveStatus;
    private String sex;
    private boolean paternityLeaveStatus;
    LocalDate leaveDate;

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    LocalDate joiningDate;

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    private Month month;

    public boolean getMaternityLeaveStatus() {
        return maternityLeaveStatus;
    }

    public void setMaternityLeaveStatus(boolean maternityLeaveStatus,LocalDate date) {
        this.maternityLeaveStatus = maternityLeaveStatus;
        this.leaveDate = date;
    }

    public boolean getPaternityLeaveStatus() {
        return paternityLeaveStatus;
    }

    public void setPaternityLeaveStatus(boolean paternityLeaveStatus, LocalDate date) {
        this.paternityLeaveStatus = paternityLeaveStatus;
        this.leaveDate = date;
    }



    public Employee(int empId, String empName, String empDesignation, int leaveBalance, int compOffBalance, int leavesTaken, String sex) {
        this.empId = empId;
        this.empName = empName;
        this.empDesignation = empDesignation;
        this.leaveBalance = leaveBalance;
        this.compOffBalance = compOffBalance;
        this.leavesTaken = leavesTaken;
        this.sex = sex;
    }

    public int getCompOffBalance() {
        return compOffBalance;
    }

    public void setCompOffBalance(int compOffBalance) {
        this.compOffBalance = compOffBalance;
    }

    private int compOffBalance = 0;
//    public HashMap<Integer, Integer> getLeaveBalance1() {
//        return leaveBalance1;
//    }
//
//
//    public void setLeaveBalance1(HashMap<Integer, Integer> leaveBalance1) {
//        this.leaveBalance1 = leaveBalance1;
//    }
//
//    HashMap<Integer,Integer> leaveBalance1=new HashMap<Integer, Integer>();

    public int getLeavesTaken(Month m) {
        return leavesTaken;
    }

    public void setLeavesTaken(int leavesTaken, Month m) {
        this.leavesTaken = leavesTaken;
        this.month = m;
    }

    private int leavesTaken;
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public Employee() {}
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
