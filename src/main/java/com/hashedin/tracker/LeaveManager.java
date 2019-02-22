package com.hashedin.tracker;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class LeaveManager {
    CompoffManager compoffManager = new CompoffManager();

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

         if(e.getLeaveType() == LeaveType.maternityLeave && e.getSex()
                .equals ("female") && DAYS.between(e.getJoiningDate(),e.getLeaveStartDate())>=180)  {
             e.setLeaveBalance((int)DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(6)));
            e.setLeaveEndDate(LocalDate.now().plusMonths(6));
            setBlanketCoverageStatus(true);
            ifBlanketCoverage(request,e);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted For maternity");
        }

         if(e.getLeaveType() == LeaveType.paternityLeave && e.getSex().equals("male")) {
             e.setLeaveBalance((int)DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(1)));
            e.setLeaveEndDate(LocalDate.now().plusMonths(1));

            setBlanketCoverageStatus(true);
            ifBlanketCoverage(request,e);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave Granted for paternity");
        }
         if(e.getLeaveType() ==  LeaveType.general && interval <= e.getLeaveBalance()) {
           e.reduceLeaveBalance((int) interval);
            setBlanketCoverageStatus(false);
           ifNonBlanketCoverage(request, e);
            return new LeaveResponse(LeaveStatus.ACCEPTED, "General Leave Granted");
        }

        LocalDate joining = e.getJoiningDate();
        long actualDuration = Duration.between(e.getLeaveStartDate().atStartOfDay(),
                e.getLeaveEndDate().atStartOfDay()).toDays();
        long minDuration = Duration.between(e.getLeaveStartDate().atStartOfDay(),
                e.getLeaveEndDate().plusMonths(1).atStartOfDay()).toDays();
        long maxDuration = Duration.between(e.getLeaveStartDate().atStartOfDay(),
                e.getLeaveEndDate().plusMonths(3).atStartOfDay()).toDays();
        long advance = Duration.between(LocalDate.now().atStartOfDay(),
                e.getLeaveStartDate().atStartOfDay()).toDays();
        long expected = Duration.between(LocalDate.now().atStartOfDay(),
                e.getLeaveStartDate().plusMonths(3).atStartOfDay()).toDays();
        long expYears = Duration.between(joining.atStartOfDay(),
                e.getJoiningDate().plusYears(2).atStartOfDay()).toDays();
        long yearOfExperience = Duration.between(joining.atStartOfDay(),
                e.getLeaveStartDate().atStartOfDay()).toDays();

        if (e.getLeaveType() == LeaveType.SABBATICAL) {
            setBlanketCoverageStatus(true);
            ifBlanketCoverage(request, e);
            if (expYears < yearOfExperience) {
                return new LeaveResponse(LeaveStatus.ACCEPTED, "you have more than 2 years of experience");
            }
            if (advance > 45 || advance < expected) {
                return new LeaveResponse(LeaveStatus.ACCEPTED, "leave granted since asked before 45 days to 3 months ");
            }
            if (actualDuration > minDuration && actualDuration < maxDuration) {
                return new LeaveResponse(LeaveStatus.ACCEPTED, "leave duration is minimum 1 and maximum 3 months");
            }

        }
        if(e.getLeaveType() == LeaveType.optionalLeave && e.getOptionalLeavesTaken()<e.getOptionalLeaveBalance()/2) {
            return new LeaveResponse(LeaveStatus.ACCEPTED, "you have optional leaves available");
        }
        if(e.getLeaveType() == LeaveType.compOff  )
        {
            logExtraWork(e,compoffManager.getWorkedDateStartTime(),compoffManager.getWorkedDateEndTime());
            if(e.getCompOffBalance() > interval) {
                return new LeaveResponse(LeaveStatus.ACCEPTED, "CompOff Leave Granted");
            }
        }
        if(interval > e.getLeaveBalance() ) {
            return new LeaveResponse(LeaveStatus.REJECTED, "INSUFFICIENT BALANCE");
        }
        e.setLeaveEndDate(LocalDate.now());
        return new LeaveResponse(LeaveStatus.REJECTED, "Unknown Error");
    }






    void logExtraWork(Employee e, LocalDateTime workedDateStartTime, LocalDateTime workedDateEndTime) {
        CompoffManager status = new CompoffManager();
        int flg=0;
        int day = workedDateStartTime.getDayOfMonth();
        LocalDateTime tempDateTime = LocalDateTime.from(workedDateStartTime);
        long hours = tempDateTime.until(workedDateEndTime, ChronoUnit.HOURS);
        int balance=e.getCompOffBalance();
        for(int i = 0; i < status.findWeekend(workedDateStartTime.getMonth()).size(); i++) {
            // Checks if worked on weekend
            if (day == status.findWeekend(workedDateStartTime.getMonth()).get(i)) {
                balance++;
//                status.getCompOffLog().add(workedDateStartTime);
            }
            // Checks if worked more than 8 hours
            else if (hours > 8)
            {
                flg=1;
                break;
            }
        }
    if(flg==1){
        e.setCompOffBalance(++balance);
    }
    else {
        e.setCompOffBalance(balance);
    }
    }

// Non Blanket coverage logic.
    public void ifNonBlanketCoverage(LeaveRequest request, Employee e) {
        CompoffManager compoffManager = new CompoffManager();
        int daysBetween =(int) DAYS.between(e.getLeaveStartDate(), e.getLeaveEndDate());
        int holidaysBetween = compoffManager.numberOfWeekendContained(e.getLeaveStartDate(),e.getLeaveEndDate());
        e.setLeavesTaken(daysBetween - holidaysBetween, LocalDate.now().getMonth());
    }

    // Blanket Coverage logic.
    public void ifBlanketCoverage ( LeaveRequest request, Employee e) {
        CompoffManager compoffManager = new CompoffManager();
        int daysBetween =(int) DAYS.between(e.getLeaveStartDate(), e.getLeaveEndDate());
        e.setLeavesTaken(daysBetween, LocalDate.now().getMonth());
    }



}
