package com.hashedin.tracker;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class LeaveManagerTest {

    @Test
    public void testForFourDays() {
        Employee e = new Employee(1,"Someone","Developer",5,0,0, "male");
         LeaveManager manager = new LeaveManager();
         LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(2));
        LeaveResponse response = manager.applyForLeave(request,e,3,LeaveType.general);
        assertEquals("Approved",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test
    public void testFemaleMaternityLeave() {
        Employee e = new Employee(1,"name","developer",2,"female",false,LocalDate.now(),LocalDate.of(2018,1,1),0,0);

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.maternityLeave);
        assertEquals("Not Approved",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test
    public void testMaleMaternityLeave() {
        Employee e = new Employee(1,"Someone","Developer",0,0,0,"male");

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.maternityLeave);
        assertEquals("Not Approved",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void testFemalePaternityLeave() {
        Employee e = new Employee(1,"Someone","Developer",0,0,0,"female");

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.paternityLeave);
        assertEquals("Not Approved",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void testMalePaternityLeave() {
        Employee e = new Employee(1,"Someone","Developer",0,0,0,"male");

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.paternityLeave);
        assertEquals("Not Approved",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test (expected = IllegalArgumentException.class)
    public void endDateBeforeStartDate() {
        Employee e = new Employee(1,"Someone","Developer",6,0,0,"male");

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now().plusDays(5), LocalDate.now());
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.general);
        assertEquals("Not Approved",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void compOffCheck() {
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        CompoffManager compoffManager = new CompoffManager();
        Employee e = new Employee(1,"Name","Developer",2,0,0,"male");
        manager.logExtraWork(e,LocalDate.of(2019, Month.FEBRUARY,20),LocalDate.of(2019,Month.FEBRUARY,16));
        assertEquals("CompOff Balance:",1,manager.compOffBalance(e,LocalDate.now()));
    }
//    @Test
//    public void testLeaveBalance1HashMap() {
//        Employee e = new Employee(1,"Someone","Developer",6,"male");
//
//        LeaveManager manager = new LeaveManager();
//        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
//        LeaveResponse response = manager.applyForLeave1(request,e,5,LeaveType.OOO,3);
//        assertEquals("Not Approved",LeaveStatus.ACCEPTED,response.getStatus());
//    }
}