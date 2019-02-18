package com.hashedin.tracker;
import java.time.*;
import java.time.temporal.ChronoUnit;
public class LeaveManager {



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
        else if(type == LeaveType.maternityLeave && e.getSex().equals ("female")) {
            e.setLeaveBalance(180);
//            e.setLeaveBalance1(e.leaveBalance1.);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
        }
        else if(type == LeaveType.paternityLeave && e.getSex().equals("male")) {
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


    public int compOffBalance(Employee e) {

        return 0;
    }
    CompoffStatus logExtraHours(LocalDateTime startTime, LocalDateTime endTime) {

        return null;
    }
/*
 *
 *
 *
 *
 */

//    public LeaveResponse applyForLeave1(LeaveRequest request, Employee e, int numberOfDays, LeaveType type, int month) {
//        Period interval = Period.between(request.getStartDate(), request.getEndDate());
//
//        if(request.getStartDate().isAfter(request.getEndDate())) {
//            throw new IllegalArgumentException("Start date >= end date");
//        }
//        else if(  interval.getDays() <= e.leaveBalance1.get(month) ) {
//            e.leaveBalance1.replace(month,2,2-interval.getDays());
//            e.setLeaveBalance1(e.leaveBalance1);
//            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
//
//        }
//        return new LeaveResponse(LeaveStatus.REJECTED, "Unknown Error");
//    }

}
