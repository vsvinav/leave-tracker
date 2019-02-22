package com.hashedin.tracker;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class LeaveAccrualManagerTest {
    @Test
    public void testForMonthlyLeaveCredit() {
        Employee e = new EmployeeMockData().getEmployeeDetails(5);
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",4,e.getLeaveBalance());
    }
    @Test
    public void testForPaternityLeaves() {
        Employee e = new EmployeeMockData().getEmployeeDetails(3);
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",(int) ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(1)),e.getLeaveBalance());
    }
    @Test
    public void testForMaternityLeavesWhenSexMale() {
        Employee e = new EmployeeMockData().getEmployeeDetails(5);
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",4,e.getLeaveBalance());
    }
    @Test
    public void testForMaternityLeaves() {
        Employee e = new EmployeeMockData().getEmployeeDetails(2);
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",(int)ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(6))
                ,e.getLeaveBalance());
    }

    @Test
    public void testForNewEmployee() {
        Employee e = new EmployeeMockData().getEmployeeDetails(7);
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
        accrualManager.creditLeaves(e);
        assertEquals("leaves not credited",0,e.getLeaveBalance());
    }
    @Test
    public void testIfLeavesTaken() {
        Employee e = new EmployeeMockData().getEmployeeDetails(8);
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",3,e.getLeaveBalance());
    }
}