package com.hashedin.tracker;
import java.time.*;

public class LeaveManager {


    public LeaveResponse applyForLeave(LeaveRequest request, Employee e, int numberOfDays, LeaveType type) {
        Period interval = Period.between(request.getStartDate(), request.getEndDate());


        if(request.getStartDate().isAfter(request.getEndDate())) {
            throw new IllegalArgumentException("Start date >= end date");
        }
        else if(interval.getDays() <= e.getLeaveBalance() ) {
            request.list.add(request.getStartDate());
            request.list.add(request.getEndDate());
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
        else if(type ==  LeaveType.general && interval.getDays() <= e.getLeaveBalance()) {
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
        asOfDate=LocalDate.now();

        return e.getLeaveBalance();
    }


    public int compOffBalance(Employee e, LocalDate asOfDay) {
        return e.getCompOffBalance();
    }
    void logExtraHours(Employee e,LocalDate compOffDate, LocalDate workedDay) {
        CompoffManager status = new CompoffManager();
        int day = workedDay.getDayOfMonth();
        int balance=e.getCompOffBalance();
        for(int i = 0; i < status.findWeekend(workedDay.getMonth()).size(); i++) {
            if (day == status.findWeekend(workedDay.getMonth()).get(i)) {
                balance++;
            }
        }

        e.setCompOffBalance(balance);

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
