package com.hashedin.tracker;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LeaveManagerTest {

    @Test
    public void testForLeavesUnderLeaveBalance() {
        Employee e = new EmployeeMockData().getEmployeeDetails(1);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(e.getEmpId(), e.getLeaveStartDate(), e.getLeaveEndDate());
        LeaveResponse response = manager.applyForLeave(request, e);
        assertEquals("Approved", LeaveStatus.ACCEPTED, response.getStatus());
    }

    @Test
    public void testFemaleMaternityLeave() {
        Employee e = new EmployeeMockData().getEmployeeDetails(2);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(e.getEmpId(), e.getLeaveStartDate(), e.getLeaveEndDate());
        LeaveResponse response = manager.applyForLeave(request, e);
        assertEquals(" Approved", LeaveStatus.ACCEPTED, response.getStatus());
    }

    @Test
    public void testMaleMaternityLeave() {
        Employee e = new EmployeeMockData().getEmployeeDetails(4);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(e.getEmpId(), e.getLeaveStartDate(), e.getLeaveEndDate());
        LeaveResponse response = manager.applyForLeave(request, e);
        assertEquals("Not Approved", LeaveStatus.REJECTED, response.getStatus());
    }

    @Test
    public void testFemalePaternityLeave() {
        Employee e = new EmployeeMockData().getEmployeeDetails(5);

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(e.getEmpId(), e.getLeaveStartDate(), e.getLeaveEndDate());
        LeaveResponse response = manager.applyForLeave(request, e);
        assertEquals("Not Approved", LeaveStatus.REJECTED, response.getStatus());
    }

    @Test
    public void testMalePaternityLeave() {
        Employee e = new EmployeeMockData().getEmployeeDetails(3);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(e.getEmpId(), e.getLeaveStartDate(), e.getLeaveEndDate());
        LeaveResponse response = manager.applyForLeave(request, e);
        assertEquals("Not Approved" + e.getEmpId(), LeaveStatus.ACCEPTED, response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void endDateBeforeStartDate() {
        Employee e = new EmployeeMockData().getEmployeeDetails(6);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(e.getEmpId(), e.getLeaveEndDate(), e.getLeaveStartDate());
        LeaveResponse response = manager.applyForLeave(request, e);
        assertEquals("Not Approved", LeaveStatus.REJECTED, response.getStatus());
    }

    @Test
    public void compOffCheck() {
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        CompoffManager compoffManager = new CompoffManager();
        Employee e = new EmployeeMockData().getEmployeeDetails(1);
//        manager.logExtraWork(e, LocalDate.of(2019, Month.FEBRUARY, 20), LocalDate.of(2019, Month.FEBRUARY, 16));
//        assertEquals("CompOff Balance:", 1, manager.compOffBalance(e, LocalDate.now()));
    }

    @Test
    public void checkFemaleMaternityLeaveFromJoining() {
        Employee e = new EmployeeMockData().getEmployeeDetails(9);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(e.getEmpId(), e.getLeaveEndDate(), e.getLeaveStartDate());
        LeaveResponse response = manager.applyForLeave(request, e);
        assertEquals("Not Approved", LeaveStatus.REJECTED, response.getStatus());
    }

    @Test
    public void checkSabbaticalLeave() {
        Employee e = new EmployeeMockData().getEmployeeDetails(10);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(e.getEmpId(), e.getLeaveEndDate(), e.getLeaveStartDate());
        LeaveResponse response = manager.applyForLeave(request, e);
        assertEquals("Not Approved", LeaveStatus.ACCEPTED, response.getStatus());
    }
}
