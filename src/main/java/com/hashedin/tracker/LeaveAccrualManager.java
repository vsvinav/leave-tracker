package com.hashedin.tracker;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class LeaveAccrualManager {

    public void creditLeaves(Employee e){
        if( e.getLeaveType() == LeaveType.paternityLeave && e.getSex().equals("male")) {
                e.setLeaveBalance(30);
                return;
        }
         if(e.getLeaveType() == LeaveType.maternityLeave && e.getSex().equals("female"))
        {
            e.setLeaveBalance((int)DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(6)));
            return;
        }
        if(e.getJoiningDate().getMonth() == LocalDate.now().getMonth()){
            e.setLeaveBalance(0);
            return;
        }
         if(e.getLeavesTaken(LocalDate.now().getMonth())>=0 && e.getLeavesTaken(LocalDate.now().getMonth())<=e.getLeaveBalance()) {

            e.setLeaveBalance(e.getLeaveBalance()-e.getLeavesTaken(LocalDate.now().getMonth())+2);
        }


    }
/*
for(int i=0; i<optionalHolidays.size(); i++){
                if(optionalHolidays.get(i) == LocalDate.now() && optionalCount > request.getEmployee().getOptionalHoliday()/2){
                    return new LeaveResponse(LeaveStatus.REJECTED, "Already have maximum leaves for optional holiday");
                }
            }
 */

}





