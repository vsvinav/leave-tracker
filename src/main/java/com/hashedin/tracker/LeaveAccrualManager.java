package com.hashedin.tracker;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeaveAccrualManager {

    public void creditLeaves(Employee e){
        if( e.getPaternityLeaveStatus()) {
                e.setLeaveBalance(30);
        }
        else if(e.getMaternityLeaveStatus())
        {
            e.setLeaveBalance(180);
        }
        else if(e.getLeavesTaken(LocalDate.now().getMonth())>0 && e.getLeavesTaken(LocalDate.now().getMonth())<=e.getLeaveBalance()) {

            e.setLeaveBalance(e.getLeaveBalance()-e.getLeavesTaken(LocalDate.now().getMonth())+2);
        }
        else if(e.getJoiningDate().getMonth() == LocalDate.now().getMonth()){
            e.setLeaveBalance(0);
        }
        else {
           e.setLeaveBalance(e.getLeaveBalance()+2);
        }
    }


}





