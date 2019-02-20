package com.hashedin.tracker;


import java.time.LocalDate;
import java.util.ArrayList;

public class LeaveRequest {
    private int empId;
    private LocalDate startDate, endDate;

    public Employee getEmployee() {
        return e;
    }

    public void setEmployee(Employee e) {
        this.e = e;
    }

    Employee e;
    ArrayList<LocalDate> list = new ArrayList<>();
    public LeaveRequest(int empId, LocalDate startDate, LocalDate endDate) {
        this.empId = empId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LeaveRequest() {
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}
