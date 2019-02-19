package com.hashedin.tracker;
import java.time.temporal.ChronoUnit;
import java.time.Period;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Employee {

    private int empId;
    private String empName;
    private String empDesignation;
    private int leaveBalance=2;

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
    public Employee() {
//        for(int i=1;i<=12;i++)
//            leaveBalance1.put(i,2);
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
