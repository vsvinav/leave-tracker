package com.hashedin.tracker;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LeaveManagerTest {

    @Test
    public void testForFourDays() {
        Employee e = new Employee(1,"Someone","Developer",5, "male");
         LeaveManager manager = new LeaveManager();
         LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(2));
        LeaveResponse response = manager.applyForLeave(request,e,3,LeaveType.OOO);
        assertEquals("Approved",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test
    public void testFemaleMaternityLeave() {
        Employee e = new Employee(1,"Someone","Developer",0,"female");

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.maternityLeave);
        assertEquals("Not Approved",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test
    public void testMaleMaternityLeave() {
        Employee e = new Employee(1,"Someone","Developer",0,"male");

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.maternityLeave);
        assertEquals("Not Approved",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test
    public void testFemalePaternityLeave() {
        Employee e = new Employee(1,"Someone","Developer",0,"female");

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.paternityLeave);
        assertEquals("Not Approved",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test
    public void testMalePaternityLeave() {
        Employee e = new Employee(1,"Someone","Developer",0,"male");

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(5));
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.paternityLeave);
        assertEquals("Not Approved",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test
    public void endDateBeforeStartDate() {
        Employee e = new Employee(1,"Someone","Developer",6,"male");

        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(1, LocalDate.now().plusDays(5), LocalDate.now());
        LeaveResponse response = manager.applyForLeave(request,e,5,LeaveType.OOO);
        assertEquals("Not Approved",LeaveStatus.REJECTED,response.getStatus());
    }
}