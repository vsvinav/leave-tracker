package com.hashedin.tracker;
import java.time.*;
import java.time.temporal.ChronoUnit;
public class LeaveManager {
//     enum leaveType {
//        OOO, compOff, maternityLeave, paternityLeave;
//    }
//    public void apply(Employee e, leaveType type) {
////        System.out.println("Leave Allocated of type:" + type + " to\nID: " + e.getEmpId() + "\nName:" + e.getEmpName()
////        + "\nDesignation:" + e.getEmpDesignation() + "\nLeave Balance:" + e.reduceLeaveBalance());
//        System.out.println("Allocated");
//    }
//
//    public void checkForLeaves(Employee e ) {
//
//    }

    public LeaveResponse applyForLeave(LeaveRequest request, Employee e, int numberOfDays, LeaveType type) {
        Period interval = Period.between(request.getStartDate(), request.getEndDate());
        if(request.getStartDate().isAfter(request.getEndDate())) {
            throw new IllegalArgumentException("Start date >= end date");
        }
        else if(interval.getDays() <= e.getLeaveBalance() ) {
            e.reduceLeaveBalance(numberOfDays);
            e.setLeavesTaken(numberOfDays);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
        }
        else if(type == LeaveType.maternityLeave && e.getSex() == "female") {
            e.setLeaveBalance(180);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
        }
        else if(type == LeaveType.paternityLeave && e.getSex() == "male") {
            e.setLeaveBalance(30);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
        }
        else if(type ==  LeaveType.OOO && interval.getDays() <= e.getLeaveBalance()) {
           e.reduceLeaveBalance(numberOfDays);
            e.setLeavesTaken(numberOfDays);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
        }
        else if(interval.getDays() > e.getLeaveBalance() ) {
            return new LeaveResponse(LeaveStatus.REJECTED, "INSUFFICIENT BALANCE");
        }
        return new LeaveResponse(LeaveStatus.REJECTED, "Unknown Error");
    }
//    LeaveRequest request = new LeaveRequest(1, LocalDate.now(), LocalDate.now().plusDays(2));

    public int leaveBalance(Employee e, LocalDate asOfDate) {
//        if( e.getLeaveBalance() <= 0)
//            return 0;
//        else if ((ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate()))>e.getLeaveBalance()){
//        }
        return e.getLeaveBalance();
    }

    public int compOffBalance(Employee employee, LocalDate asOfDate) {
        return 0;
    }

}
