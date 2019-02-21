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


    public LeaveResponse applyForLeave(LeaveRequest request, Employee e) {
        long interval = (int)DAYS.between(e.getLeaveStartDate(),e.getLeaveEndDate());
        if(e.getLeaveStartDate().isAfter(e.getLeaveEndDate())) {
            throw new IllegalArgumentException("Start leaveStartDate >= end leaveStartDate");
        }

        else if(e.getLeaveType() == LeaveType.maternityLeave && e.getSex().equals ("female") && DAYS.between(e.getJoiningDate(),e.getLeaveStartDate())>=180)  {
            e.setLeaveBalance(180);
            e.setLeaveEndDate(LocalDate.now().plusMonths(6));
            setBlanketCoverageStatus(true);
            ifBlanketCoverage(request,e);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted For maternity");
        }
        else if(e.getLeaveType() == LeaveType.paternityLeave && e.getSex().equals("male")) {
            e.setLeaveBalance(30);
            e.setLeaveEndDate(LocalDate.now().plusMonths(1));

            setBlanketCoverageStatus(true);
            ifBlanketCoverage(request,e);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted for paternity");
        }
        else if(e.getLeaveType() ==  LeaveType.general && interval <= e.getLeaveBalance()) {
           e.reduceLeaveBalance((int) interval);
            setBlanketCoverageStatus(false);
//           ifNonBlanketCoverage(request, e);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "General Leave Granted");
        }
        else if(interval > e.getLeaveBalance() ) {
            return new LeaveResponse(LeaveStatus.REJECTED, "INSUFFICIENT BALANCE");
        }
        LocalDate joining = e.getJoiningDate();
        long duration = Duration.between(e.getLeaveStartDate().atStartOfDay(), e.getLeaveEndDate().atStartOfDay()).toDays();
        long minDuration = Duration.between(e.getLeaveStartDate().atStartOfDay(), e.getLeaveEndDate().plusMonths(1).atStartOfDay()).toDays();
        long maxDuration = Duration.between(e.getLeaveStartDate().atStartOfDay(), e.getLeaveEndDate().plusMonths(3).atStartOfDay()).toDays();
        long advance = Duration.between(LocalDate.now().atStartOfDay(), e.getLeaveStartDate().atStartOfDay()).toDays();
        long expected = Duration.between(LocalDate.now().atStartOfDay(), e.getLeaveStartDate().plusMonths(3).atStartOfDay()).toDays();
        long expYears = Duration.between(joining.atStartOfDay(), e.getJoiningDate().plusYears(2).atStartOfDay()).toDays();
        long yearOfExperience = Duration.between(joining.atStartOfDay(), e.getLeaveStartDate().atStartOfDay()).toDays();

        if (e.getLeaveType() == LeaveType.SABBATICAL) {
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
        if(e.getLeaveType() == LeaveType.optionalLeave && e.getOptionalLeavesTaken()<e.getOptionalLeaveBalance()/2) {
            return new LeaveResponse(LeaveStatus.ACCEPTED, "you have optional leaves available");
        }

        e.setLeaveEndDate(LocalDate.now());
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
        LocalDate start = request.getLeaveStartDate();
        LocalDate end = request.getEndDate();
        LocalDate start1 = request1.getLeaveStartDate();
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
        int daysBetween =(int) DAYS.between(e.getLeaveStartDate(), e.getLeaveEndDate());
        int holidaysBetween = compoffManager.numberOfWeekendContained(request.getLeaveStartDate(),request.getEndDate());
        e.setLeavesTaken(daysBetween - holidaysBetween, LocalDate.now().getMonth());
    }

    public void ifBlanketCoverage ( LeaveRequest request, Employee e) {
        e = new EmployeeMockData().getEmployeeDetails(1);
        CompoffManager compoffManager = new CompoffManager();
        int daysBetween =(int) DAYS.between(e.getLeaveStartDate(), e.getLeaveEndDate());
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

}
