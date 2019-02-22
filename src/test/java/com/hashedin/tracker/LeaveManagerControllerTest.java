package com.hashedin.tracker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeaveManagerControllerTest {

//    @Autowired
//    EmployeeService employeeService;

    @Test
    public void testForLeavesLessThanLeaveBalance() {
//        EmployeeService employeeService = new EmployeeService();
//        employeeService.addEmployee(new EmployeeMockData().getEmployeeDetails(1));
//        Employee e = employeeService.getEmployee(1);
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
        Employee e = new EmployeeMockData().getEmployeeDetails(1);

        LeaveRequest request = new LeaveRequest(e.getEmpId(), e.getLeaveEndDate(), e.getLeaveStartDate());

        CompoffManager compoffManager = new CompoffManager();
        compoffManager.setWorkedDateStartTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        compoffManager.setWorkedDateEndTime(LocalDateTime.of(LocalDate.now().plusDays(1),LocalTime.now()));
        manager.logExtraWork(e, compoffManager.getWorkedDateStartTime(),compoffManager.getWorkedDateEndTime());
        assertEquals("CompOff Balance:", 1, e.getCompOffBalance());
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
