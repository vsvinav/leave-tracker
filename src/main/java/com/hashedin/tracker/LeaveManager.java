package com.hashedin.tracker;
import java.time.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class LeaveManager {
    public boolean isBlanketCoverageStatus() {
        return blanketCoverageStatus;
    }

    public void setBlanketCoverageStatus(boolean blanketCoverageStatus) {
        this.blanketCoverageStatus = blanketCoverageStatus;
    }

    private boolean blanketCoverageStatus;



    public LeaveResponse applyForLeave(LeaveRequest request, Employee e, int numberOfDays, LeaveType type) {
        Period interval = Period.between(request.getStartDate(), request.getEndDate());
        e = new EmployeeMockData().getEmployeeDetails(2);
        if(request.getStartDate().isAfter(request.getEndDate())) {
            throw new IllegalArgumentException("Start leaveDate >= end leaveDate");
        }
        else if(interval.getDays() <= e.getLeaveBalance() ) {

            e.reduceLeaveBalance(numberOfDays);
            e.setLeavesTaken(numberOfDays,request.getStartDate().getMonth());
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
        }
        else if(type == LeaveType.maternityLeave && e.getSex().equals ("female") && DAYS.between(e.getJoiningDate(),request.getStartDate())>=180)  {
            e.setMaternityLeaveStatus(true,request.getStartDate());
            e.setPaternityLeaveStatus(false,request.getStartDate());
            System.out.println("I am going here");
            e.setLeaveBalance(180);
            request.setEndDate(LocalDate.now().plusMonths(6));
            setBlanketCoverageStatus(true);
            ifBlanketCoverage(request,e);
//            e.setLeaveBalance1(e.leaveBalance1.);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted For maternity");
        }
        else if(type == LeaveType.paternityLeave && e.getSex().equals("male")) {
            e.setLeaveBalance(30);
            request.setEndDate(LocalDate.now().plusMonths(1));

            e.setMaternityLeaveStatus(false,request.getStartDate());
            e.setPaternityLeaveStatus(true,request.getStartDate());
            setBlanketCoverageStatus(true);
            ifBlanketCoverage(request,e);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
        }
        else if(type ==  LeaveType.general && interval.getDays() <= e.getLeaveBalance()) {
           e.reduceLeaveBalance(numberOfDays);
            setBlanketCoverageStatus(false);
           ifNonBlanketCoverage(request, e);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
        }
        else if(interval.getDays() > e.getLeaveBalance() ) {
            return new LeaveResponse(LeaveStatus.REJECTED, "INSUFFICIENT BALANCE");
        }
        LocalDate joining = e.getJoiningDate();
        long duration = Duration.between(request.getStartDate().atStartOfDay(), request.getEndDate().atStartOfDay()).toDays();
        long minDuration = Duration.between(request.getStartDate().atStartOfDay(), request.getStartDate().plusMonths(1).atStartOfDay()).toDays();
        long maxDuration = Duration.between(request.getStartDate().atStartOfDay(), request.getStartDate().plusMonths(3).atStartOfDay()).toDays();
        long advance = Duration.between(LocalDate.now().atStartOfDay(), request.getStartDate().atStartOfDay()).toDays();
        long expected = Duration.between(LocalDate.now().atStartOfDay(), request.getStartDate().plusMonths(3).atStartOfDay()).toDays();
        long expYears = Duration.between(joining.atStartOfDay(), e.getJoiningDate().plusYears(2).atStartOfDay()).toDays();
        long yearOfExperience = Duration.between(joining.atStartOfDay(), request.getStartDate().atStartOfDay()).toDays();

        if (type == LeaveType.SABBATICAL) {
            setBlanketCoverageStatus(true);
            ifBlanketCoverage(request, e);
            if (expYears < yearOfExperience) {
                return new LeaveResponse(LeaveStatus.ACCEPTED, "you 2 years of experience");
            }
            if (advance > 45 || advance < expected) {
                return new LeaveResponse(LeaveStatus.ACCEPTED, "leave before 45 days to 3 months ");
            }
            if (duration > minDuration || duration < maxDuration) {
                return new LeaveResponse(LeaveStatus.ACCEPTED, "leave duration is minimum 1 and maximum 3 months");
            }

        }
        if(e.getOptionalLeavesTaken()<e.getOptionalLeaveBalance()/2) {
            return new LeaveResponse(LeaveStatus.ACCEPTED, "you have optional leaves available");
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

    void logExtraWork(Employee e, LocalDate compOffDate, LocalDate workedDay) {
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
    public LeaveResponse checkDuplicate(LeaveRequest request, LeaveRequest request1) {
        LocalDate start = request.getStartDate();
        LocalDate end = request.getEndDate();
        LocalDate start1 = request1.getStartDate();
        LocalDate end1 = request1.getEndDate();

        if (request.getEmployee().getEmpId() != request1.getEmployee().getEmpId()) {
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Accepted");
        }
        if ((start1.isBefore(start) && end1.isBefore(start)) || start1.isAfter(end)) {
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Accepted");
        }
        return new LeaveResponse(LeaveStatus.REJECTED, "leaves are overlapping");

    }

    public void ifNonBlanketCoverage(LeaveRequest request, Employee e) {
        e = new EmployeeMockData().getEmployeeDetails(1);
        CompoffManager compoffManager = new CompoffManager();
        int daysBetween =(int) DAYS.between(request.getStartDate(), request.getEndDate());
        int holidaysBetween = compoffManager.numberOfWeekendContained(request.getStartDate(),request.getEndDate());
        e.setLeavesTaken(daysBetween - holidaysBetween, LocalDate.now().getMonth());
    }

    public void ifBlanketCoverage ( LeaveRequest request, Employee e) {
        e = new EmployeeMockData().getEmployeeDetails(1);
        CompoffManager compoffManager = new CompoffManager();
        int daysBetween =(int) DAYS.between(request.getStartDate(), request.getEndDate());
        e.setLeavesTaken(daysBetween, LocalDate.now().getMonth());
    }


/*
 public boolean logExtraWork(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime tempDateTime = LocalDateTime.from(startTime);
        long hours = tempDateTime.until(endTime, ChronoUnit.HOURS);
        if (hours >= 8) {
            return true;
        } else
            return false;

    }


*/

//    public LeaveResponse applyForLeave1(LeaveRequest request, Employee e, int numberOfDays, LeaveType type, int month) {
//        Period interval = Period.between(request.getStartDate(), request.getEndDate());
//
//        if(request.getStartDate().isAfter(request.getEndDate())) {
//            throw new IllegalArgumentException("Start leaveDate >= end leaveDate");
//        }
//        else if(  interval.getDays() <= e.leaveBalance1.get(month) ) {
//            e.leaveBalance1.replace(month,2,2-interval.getDays());
//            e.setLeaveBalance1(e.leaveBalance1);
//            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted");
//
//        }
//        return new LeaveResponse(LeaveStatus.REJECTED, "Unknown Error");
//    }
/*
What is it you want me to tell ya?
I'm not the failure
I would rather live and let be
But you can't make the right kinda threat to
Push me to let you
No, you can't intimidate me
You disrespect me so clearly
Now you better hear me
That is not the way it goes down
 */

}
