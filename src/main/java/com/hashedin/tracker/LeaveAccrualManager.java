package com.hashedin.tracker;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeaveAccrualManager {

    public void creditLeaves(Employee e){
        if( e.getPaternityLeaveStatus() && e.getSex().equals("male")) {
                e.setLeaveBalance(30);
                return;
        }
         if(e.getMaternityLeaveStatus() && e.getSex().equals("female"))
        {
            e.setLeaveBalance(180);
            return;
        }
         if(e.getLeavesTaken(LocalDate.now().getMonth())>=0 && e.getLeavesTaken(LocalDate.now().getMonth())<=e.getLeaveBalance()) {

            e.setLeaveBalance(e.getLeaveBalance()-e.getLeavesTaken(LocalDate.now().getMonth())+2);
        }
         if(e.getJoiningDate().getMonth() == LocalDate.now().getMonth()){
            e.setLeaveBalance(0);
        }
    }


}





