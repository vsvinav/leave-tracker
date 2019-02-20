package com.hashedin.tracker;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class LeaveAccrualManagerTest {
    @Test
    public void testForLeaveCredit() {
        Employee e = new Employee(1,"name","developer",0,false,"male",false,LocalDate.now(),LocalDate.of(2019,1,1),0,0);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(2));
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
//        LeaveResponse response = manager.applyForLeave(request,e,3,LeaveType.general);
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",2,e.getLeaveBalance());
    }
    @Test
    public void testForPaternityLeaves() {
        Employee e = new Employee(1,"name","developer",0,false,"male",true,LocalDate.now(),LocalDate.of(2019,1,1),0,0);
//        LeaveManager manager = new LeaveManager();
//        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(2));
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
//        LeaveResponse response = manager.applyForLeave(request,e,3,LeaveType.general);
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",30,e.getLeaveBalance());
    }
    @Test
    public void testForMaternityLeavesWhenSexMale() {
        Employee e = new Employee(1,"name","developer",0,true,"male",false,LocalDate.now(),LocalDate.of(2019,1,1),0,0);
//        LeaveManager manager = new LeaveManager();
//        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(2));
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
//        LeaveResponse response = manager.applyForLeave(request,e,3,LeaveType.general);
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",2,e.getLeaveBalance());
    }
    @Test
    public void testForMaternityLeaves() {
        Employee e = new Employee(1,"name","developer",0,true,"female",false,LocalDate.now(),LocalDate.of(2019,1,1),0,0);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(2));
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
//        LeaveResponse response = manager.applyForLeave(request,e,3,LeaveType.general);
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",180
                ,e.getLeaveBalance());
    }

    @Test
    public void testForNewEmployee() {
        Employee e = new Employee(1,"name","developer",0,false,"male",false,LocalDate.now(),LocalDate.of(2019,2,1),0,0);
//        LeaveManager manager = new LeaveManager();
//        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(2));
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
//        LeaveResponse response = manager.applyForLeave(request,e,3,LeaveType.general);
        accrualManager.creditLeaves(e);
        assertEquals("leaves not credited",0,e.getLeaveBalance());
    }
    @Test
    public void testIfLeavesTaken() {
        Employee e = new Employee(1,"name","developer",2,false,"male",false,LocalDate.now(),LocalDate.of(2019,1,1),0,1);
//        LeaveManager manager = new LeaveManager();
//        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(2));
        LeaveAccrualManager accrualManager = new LeaveAccrualManager();
//        LeaveResponse response = manager.applyForLeave(request,e,3,LeaveType.general);
        accrualManager.creditLeaves(e);
        assertEquals("leaves credited",3,e.getLeaveBalance());
    }
}